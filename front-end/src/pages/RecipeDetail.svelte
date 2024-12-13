<script>
    import { onMount } from "svelte";
    import { navigate } from "svelte-routing";

    let recipeData = {
        image: null,        // 이미지 파일
        name: '',           // 메뉴 이름
        title: '',          // 레시피 제목
        category: '',       // 분류
        ingredients: '',    // 재료
        recipe: '',         // 요리 방법
        uid: ''             // 작성자 UID
    };

    let rid = null; // 레시피 ID

    // 페이지 로드 시 실행
    onMount(async () => {
        const urlParams = new URLSearchParams(window.location.search);
        rid = urlParams.get("rid");

        if (!rid) {
            alert("잘못된 접근입니다.");
            navigate("/");
        } else {
            await fetchRecipeDetails(rid); // 레시피 데이터 가져오기
        }
    });

    // 서버에서 레시피 상세 정보를 가져오는 함수
    async function fetchRecipeDetails(rid) {
        try {
            const response = await fetch(`http://localhost:8080/api/recipes/details`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ rid })
            });

            if (!response.ok) {
                throw new Error(`레시피를 가져오는 중 오류가 발생했습니다. 상태 코드: ${response.status}`);
            }

            const data = await response.json();
            console.log("가져온 레시피 데이터:", data);

            // 서버에서 가져온 데이터를 recipeData에 반영
            recipeData = {
                image: data.rimg_src + data.rimg_name,
                name: data.menu_name,
                title: data.recipe_title,
                category: data.category,
                ingredients: data.ingredients,
                recipe: data.instructions,
                uid: data.uid
            };
        } catch (error) {
            console.error("레시피 가져오기 오류:", error);
            alert("레시피를 가져오는 중 오류가 발생했습니다.");
        }
    }
</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form">
            {#if } <!-- 즐겨찾기 조건 -->
            <img src="/img/favorie2.png" class="favorite"/>
            {:else}
            <img src="/img/favorite1.png" class="favorite"/>
            {/if}
            
            <div class="image-upload">
                <label for="image-upload">
                    <div class="image-placeholder">
                        {#if recipeData.image}
                            <img src={recipeData.image} alt={recipeData.title} />
                        {:else}
                            <span>이미지 없음</span>
                        {/if}
                    </div>
                </label>
            </div>

            <div class="form-container">
                <input type="text" class="input" placeholder="글 제목" bind:value={recipeData.title} readonly />
                <input type="text" class="input" placeholder="메뉴 이름" bind:value={recipeData.name} readonly />
                <input type="text" class="input" placeholder="카테고리" bind:value={recipeData.category} readonly />
                <input type="text" class="input" placeholder="재료" bind:value={recipeData.ingredients} readonly />
                <textarea class="input textarea" placeholder="쌈뽕한 요리방법" bind:value={recipeData.recipe} readonly></textarea>
            </div>

        </form>
    </div>
</main>

<style>
    .setCenter {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .form-box {
        position: absolute;
        top: 15%;
        width: 60%;
        max-width: 600px;
        background: #f1f7fe;
        overflow: hidden;
        border-radius: 15px;
        color: #333;
    }

    .form {
        position: relative;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 32px 24px 24px;
        padding-bottom: 40px;
        gap: 16px;
    }

    .favorite {
        width: 40px;
        height: 40px;
    }
    
    .image-upload {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 80%;
    }

    .image-upload label {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 80%;
    }

    .image-placeholder {
        width: 100%;
        height: 200px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: 2px dashed #ddd;
        border-radius: 8px;
        background-color: #fff;
        color: #aaa;
        cursor: pointer;
    }

    .image-placeholder img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 8px;
    }

    .input-file {
        display: none;
    }

    .form-container {
        display: flex;
        width: 90%;
        max-width: 400px;
        flex-direction: column;
        gap: 12px;
    }

    .input {
        background: none;
        border: 0;
        outline: 0;
        height: 40px;
        width: 100%;
        border-bottom: 1px solid #c9c9c9;
        font-size: 0.9rem;
        padding: 8px 15px;
        pointer-events: none;
        cursor: not-allowed;
    }

    .textarea {
        height: 120px;
        resize: none;
    }
</style>
