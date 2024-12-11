<script>
    import { navigate } from "svelte-routing";

    let recipeData = {
        image: null,        // 이미지 파일
        name: '',           // 메뉴 이름
        title: '',          // 레시피 제목
        category: '',       // 분류
        ingredients: '',    // 재료
        recipe: '',         // 요리 방법
        uid: 'rang'      // 작성자 UID (임시 값)
    };

</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form">
            <div class="image-upload">
                <label for="image-upload">
                    <div class="image-placeholder">
                        <img/>
                    </div>
                </label>
                <input id="image-upload" type="file" accept="image/*" class="input-file" on:change={(e) => recipeData.image = e.target.files[0]} />
            </div>

            <div class="form-container">
                <input type="text" class="input" placeholder="글 제목" bind:value={recipeData.title} />
                <input type="text" class="input" placeholder="메뉴 이름" bind:value={recipeData.name} />
                <input type="text" class="input" placeholder="카테고리" bind:value={recipeData.category}>
                <input type="text" class="input" placeholder="재료" bind:value={recipeData.ingredients} />
                <textarea class="input textarea" placeholder="쌈뽕한 요리방법" bind:value={recipeData.recipe}></textarea>
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
