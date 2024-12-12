<script>
    import { onMount } from "svelte";
    import { navigate } from "svelte-routing";

    let recipes = []; // 레시피 데이터
    let mid = null; // 메뉴 ID

    // 페이지 로드 시 실행
    onMount(async () => {
        const urlParams = new URLSearchParams(window.location.search);
        mid = urlParams.get("mid");

        if (!mid) {
            alert("잘못된 접근입니다.");
            navigate("/");
        } else {
            await fetchRecipes(mid); // 레시피 데이터 가져오기
        }
    });

    // 레시피 데이터를 서버에서 가져오기
    async function fetchRecipes(menuId) {
        try {
            const response = await fetch(`http://localhost:8080/api/recipes?mid=${menuId}`);
            if (!response.ok) {
                throw new Error(`레시피를 가져오는 중 오류가 발생했습니다. 상태 코드: ${response.status}`);
            }

            recipes = await response.json();
        } catch (error) {
            console.error("레시피 가져오기 오류:", error);
            alert("레시피를 가져오는 중 오류가 발생했습니다.");
        }
    }
</script>

<main class="main">
    <h1>Recipe</h1>
    {#if recipes.length === 0}
        <p>레시피를 불러오는 중이거나 레시피가 존재하지 않습니다.</p>
    {:else}
        {#each recipes as recipe (recipe.rid)}
            <div class="element" on:click={() => navigate(`/recipedetail?rid=${recipe.rid}`)}>
                <img src={recipe.rimg_src + recipe.rimg_name} alt={recipe.recipe_title} />
                <div class="stringBox">
                    <span class="title">{recipe.recipe_title}</span>
                    <span class="subtitle">{recipe.ingredients}</span>
                    <span class="subtitle">작성자 - {recipe.uid}</span>
                </div>
            </div>
        {/each}
    {/if}
</main>

<style>
    .main {
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
    }

    .element {
        display: flex;
        flex-direction: row;
        gap: 20px;
        align-items: center;
        justify-content: space-between;
        border-bottom: 1px solid #b3b3b3;
        padding-right: 20px;
        cursor: pointer;
    }

    .element img {
        border-radius: 5px;
        object-fit: cover;
        height: 100px;
        width: 100px;
        margin: 15px;
    }

    .stringBox {
        display: flex;
        flex-direction: column;
        flex: 1;
        overflow: hidden;
    }

    .stringBox .title {
        font-size: x-large;
        font-weight: bolder;
    }

    .stringBox .subtitle {
        color: #a9a9a9;
        font-size: 16px;
        font-family: 'Inter';
    }

    span {
        cursor: pointer;
        user-select: none;
    }
</style>
