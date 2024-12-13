<script>
    import { onMount } from "svelte";

    let recipes = []; // 사용자 레시피 데이터
    let uid = ""; // 현재 로그인된 UID (임시로 하드코딩)

    // UID는 실제로는 로그인 시 세션이나 JWT 토큰에서 가져옵니다.
    onMount(async () => {
        uid = "rang"; // 예: localStorage.getItem('uid')로 대체
        await fetchRecipes();
    });

    async function fetchRecipes() {
        try {
            const response = await fetch("http://113.198.238.115:8080/api/recipes/by-uid", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ uid })
            });

            if (!response.ok) {
                throw new Error(`레시피를 가져오는 중 오류가 발생했습니다. 상태 코드: ${response.status}`);
            }

            const data = await response.json();
            recipes = data;
            console.log("가져온 레시피 데이터:", recipes);
        } catch (error) {
            console.error("레시피 가져오기 오류:", error);
        }
    }

    function navigateToUpload() {
        window.location.href = '/upload';
    }

    function editData(recipe) {
        console.log("Edit:", recipe);
        // Edit 페이지로 이동
    }

    function deleteData(recipe) {
        console.log("Delete:", recipe);
        // 삭제 로직 추가
    }
</script>

<main class="main">
    <h1>My Recipes</h1>
    {#if recipes.length === 0}
        <p>레시피를 불러오는 중이거나 등록된 레시피가 없습니다.</p>
    {:else}
        {#each recipes as recipe (recipe.rid)}
            <div class="element">
                {#if recipe.rimg_src}
                    <img src={recipe.rimg_src + recipe.rimg_name} alt={recipe.recipe_title} />
                {:else}
                    <img src={'/img/noImg.png'}/>  
                {/if}
                <div class="stringBox">
                    <span class="title">{recipe.recipe_title}</span>
                    <span class="subtitle">{recipe.ingredients}</span>
                </div>
                <div class="contentBox">
                    <span class="edit" on:click={() => editData(recipe)}>Edit</span>
                    <span class="delete" on:click={() => deleteData(recipe)}>Delete</span>
                </div>
            </div>
        {/each}
        <div class="element">
            <span class="upload" on:click={navigateToUpload}>등록하기</span>
        </div>
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
    }

    .element img {
        border-radius: 5px;
        object-fit: cover;
        height: 100px;
        width: 100px;
        margin: 15px;
    }

    .element .edit {
        font-size: 20px;
    }

    .element .delete {
        font-size: 20px;
        color: red;
    }

    .element .upload {
        display: flex;
        flex: 1;
        align-items: center;
        justify-content: center;
        height: 130px;
        font-size: 30px;
        font-weight: bold;
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
        font-size: 16px;
        font-family: 'Inter';
    }

    .contentBox {
        display: flex;
        gap: 10px;
        flex-direction: row;
        align-items: center;
    }

    span {
        cursor: pointer;
        user-select: none;
    }
</style>