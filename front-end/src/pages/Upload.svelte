<script>
    import { navigate } from "svelte-routing";
    let uploadData = {
        image: null,
        title: '',
        menu: '',
        category: '',
        ingredients: '',
        recipe: '',
    };

    async function handleUpload() {
        if (!uploadData.image) {
            alert("이미지를 선택해주세요!");
            return;
        }

        const formData = new FormData();
        formData.append("image", uploadData.image);
        formData.append("title", uploadData.title);
        formData.append("menu", uploadData.menu);
        formData.append("category", uploadData.category);
        formData.append("ingredients", uploadData.ingredients);
        formData.append("recipe", uploadData.recipe);

        try {
            const response = await fetch('http://localhost:8080/api/upload', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                throw new Error(`서버 오류: ${response.status}`);
            }

            const result = await response.json();
            console.log('업로드 성공', result);
            alert("업로드 성공!");
        } catch (error) {
            console.error('업로드 중 오류 발생', error);
            alert("업로드 실패");
        }
    }


</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form" on:submit|preventDefault={handleUpload}>
            <div class="image-upload">
                <label for="image-upload">
                    <div class="image-placeholder">
                        {#if uploadData.image}
                            <img src={URL.createObjectURL(uploadData.image)} alt="미리보기" />
                        {:else}
                            <span>이미지 첨부</span>
                        {/if}
                    </div>
                </label>
                <input id="image-upload" type="file" accept="image/*" class="input-file" on:change={(e) => uploadData.image = e.target.files[0]} />
            </div>

            <div class="form-container">
                <input type="text" class="input" placeholder="제목" bind:value={uploadData.title} />
                <input type="text" class="input" placeholder="메뉴" bind:value={uploadData.menu} />
                <select class="input" bind:value={uploadData.category}>
                    <option value="" disabled selected>분류</option>
                    <option value="밥류">밥류</option>
                    <option value="면류">면류</option>
                    <option value="국/찌개">국/찌개</option>
                    <option value="기타">기타</option>
                </select>
                <input type="text" class="input" placeholder="재료" bind:value={uploadData.ingredients} />
                <textarea class="input textarea" placeholder="쌈뽕한 요리방법" bind:value={uploadData.recipe}></textarea>
            </div>

            <div class="button-group">
                <button type="button" class="cancel-btn" on:click={() => navigate("/")}>취소</button>
                <button type="submit" class="submit-btn" on:click={() => handleUpload}>확인</button>
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
    }

    .textarea {
        height: 80px;
        resize: none;
    }

    .button-group {
        display: flex;
        justify-content: space-between;
        gap: 24px;
        width: 50%;
    }

    .cancel-btn, .submit-btn {
        flex: 1;
        background-color: #ccc;
        color: #fff;
        border: 0;
        border-radius: 8px;
        padding: 10px;
        font-size: 1rem;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .submit-btn {
        background-color: #0066ff;
    }

    .submit-btn:hover {
        background-color: #005ce6;
    }

    .cancel-btn:hover {
        background-color: #aaa;
    }
</style>
