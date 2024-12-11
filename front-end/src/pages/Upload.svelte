<script>
    import { navigate } from "svelte-routing";

    let uploadData = {
        image: null,        // 이미지 파일
        name: '',           // 메뉴 이름
        title: '',          // 레시피 제목
        category: '',       // 분류
        ingredients: '',    // 재료
        recipe: '',         // 요리 방법
        uid: 'rang'      // 작성자 UID (임시 값)
    };

    let options = [
        "음료 및 차류", "유제품류 및 빙과류", "과일류", "두류, 견과 및 종실류", "곡류, 서류 제품", "밥류", "볶음류", "면 및 만두류", "육류", "수·조·어·육류", "구이류", "튀김류", "국 및 탕류", "찜류", "찌개 및 전골류", "죽 및 스프류", "조림류", "빵 및 과자류", "젓갈류", "장아찌·절임류", "김치류",  "생채·무침류", "전·적 및 부침류", "장류, 양념류", "나물·숙채류"
    ]

    async function handleUpload() {
        if (!uploadData.image) {
            alert("이미지를 선택해주세요!");
            return;
        }

        const formData = new FormData();
        formData.append("image", uploadData.image);       // 이미지 파일
        formData.append("name", uploadData.name);         // 메뉴 이름
        formData.append("recipeTitle", uploadData.title); // 레시피 제목
        formData.append("category", uploadData.category); // 분류
        formData.append("ingredients", uploadData.ingredients); // 재료
        formData.append("instructions", uploadData.recipe);     // 요리 방법
        formData.append("uid", uploadData.uid);           // UID

        try {
            const response = await fetch('http://localhost:8080/api/recipes', {
                method: 'POST',
                body: formData
            });

            // 서버 응답 상태 확인
            if (!response.ok) {
                const errorMessage = await response.json();
                throw new Error(`${response.status} - ${errorMessage.error}: ${errorMessage.details}`);
            }

            // 성공적으로 JSON 응답 처리
            const result = await response.json();
            console.log('업로드 성공:', result);
            alert(result.message); // 성공 메시지 표시
            navigate('/'); // 업로드 성공 후 메인 페이지로 이동
        } catch (error) {
            console.error('업로드 중 오류 발생', error);
            alert("업로드 실패: " + error.message);
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
                <input type="text" class="input" placeholder="레시피 제목" bind:value={uploadData.title} />
                <input type="text" class="input" placeholder="메뉴 이름" bind:value={uploadData.name} />
                <select class="input" bind:value={uploadData.category}>
                    <option value="" disabled selected>카테고리</option>
                    {#each options as option}
                        <option value={option}>{option}</option>
                    {/each}
                </select>
                <input type="text" class="input" placeholder="재료" bind:value={uploadData.ingredients} />
                <textarea class="input textarea" placeholder="쌈뽕한 요리방법" bind:value={uploadData.recipe}></textarea>
            </div>

            <div class="button-group">
                <button type="button" class="cancel-btn" on:click={() => navigate("/")}>취소</button>
                <button type="submit" class="submit-btn">확인</button>
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
