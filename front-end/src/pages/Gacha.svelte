<script>
    import { onMount } from "svelte";

    // 선택 상태
    let meal = "음료"; // 음료, 간식, 식사
    let category = "전체"; // 전체, 밥류, 면류, 육류, 튀김류 등
    let selectedMenu = null; // 선택된 메뉴
    let photoUrl = ""; // Flickr에서 가져온 사진 URL
    const apiKey = "ee561aac775d80987baa6fa0ce000255"; // Flickr API Key
    let isLoading = false; // 로딩 상태

    // 하위 카테고리 설정
    const subCategories = {
        음료: ["전체", "음료 및 차류", "유제품류 및 빙과류"],
        간식: ["전체", "과일류", "두류, 견과 및 종실류", "빵 및 과자류", "유제품류 및 빙과류", "곡류, 서류 제품"],
        식사: ["전체", "밥류", "볶음류", "면 및 만두류", "육류", "수·조·어·육류", "구이류", "튀김류", "국 및 탕류", "찜류", "찌개 및 전골류", "죽 및 스프류", "조림류", "빵 및 과자류"],
        반찬: ["전체", "젓갈류", "장아찌·절임류", "김치류",  "생채·무침류", "전·적 및 부침류", "장류, 양념류", "나물·숙채류"],
    };

    // 초기값 설정
    let visibleSubCategories = subCategories["음료"]; // 초기 하위 카테고리

    // 상위 카테고리 선택
    function selectMainCategory(selectedMeal) {
        meal = selectedMeal;
        visibleSubCategories = subCategories[selectedMeal];
        category = "전체"; // 하위 카테고리 초기화
    }

    // 하위 카테고리 선택
    function selectSubCategory(subCategoryName) {
        category = subCategoryName;
    }

    async function pickRandomMenu() {
    try {
        isLoading = true;
        selectedMenu = null;

        // "전체"인 경우 하위 카테고리 전체를 API로 전달
        const actualCategories = category === "전체" ? subCategories[meal] : [category];
        const response = await fetch(
            `http://localhost:8080/api/menus/random?categories=${actualCategories.map(encodeURIComponent).join(",")}`
        );

        if (!response.ok) {
            throw new Error(`랜덤 메뉴를 가져오지 못했습니다. 상태 코드: ${response.status}`);
        }

        selectedMenu = await response.json();
        console.log("랜덤 선택된 메뉴:", selectedMenu);

        if (selectedMenu && selectedMenu.mname) {
            const searchKeyword = selectedMenu.mname.split("_")[0];
            await fetchPhoto(searchKeyword); // Flickr API 호출
        } else {
            throw new Error("선택된 메뉴 데이터가 유효하지 않습니다.");
        }
    } catch (error) {
        console.error("랜덤 메뉴 가져오기 오류:", error);
        alert("랜덤 메뉴를 가져오는 중 오류가 발생했습니다.");
    } finally {
        isLoading = false;
    }
}





    // Flickr API 사진 가져오기
    async function fetchPhoto(keyword) {
    try {
        console.log("Flickr API 호출 키워드:", keyword);

        const response = await fetch(
            `https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=${apiKey}&text=${encodeURIComponent(keyword)}&sort=relevance&content_type=1&per_page=3&format=json&nojsoncallback=1`
        );

        const data = await response.json();
        console.log("Flickr API 응답 데이터:", data);

        if (data.photos.photo.length > 0) {
            // 가장 관련성 높은 사진을 선택
            const photo = data.photos.photo[0]; // 첫 번째 결과 선택
            photoUrl = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg`;
            console.log("선택된 사진 URL:", photoUrl);
        } else {
            console.log("Flickr API에서 사진을 찾지 못했습니다.");
            photoUrl = ""; // 기본 이미지
        }
    } catch (error) {
        console.error("Flickr API 오류:", error);
        photoUrl = ""; // 에러 발생 시 기본 이미지
    }
}


    // 식당 찾기
    function findMenu() {
        if (!selectedMenu) {
            alert("메뉴가 선택되지 않았습니다. 메뉴를 먼저 뽑아주세요.");
            return;
        }

        const link = `https://map.naver.com/p/search/${encodeURIComponent(selectedMenu.mname)}`;
        window.open(link, "_blank");
    }

    // 레시피 찾기
    function findRecipe() {
        if (!selectedMenu) {
            alert("메뉴가 선택되지 않았습니다. 메뉴를 먼저 뽑아주세요.");
            return;
        }

        const link = `/recipe?menu=${encodeURIComponent(selectedMenu.mname)}`;
        window.location.href = link;
    }
</script>




<main>
    <div class="gacha">
        <div class="img-container">
            {#if selectedMenu}
                <img src={photoUrl} alt={selectedMenu.mname} class="menu-image" />
            {:else}
                <span class="placeholder-text">메뉴를 선택해주세요!</span>
            {/if}
        </div>

        <div class="options">
            <div class="option">
                <span class:active={meal === "음료"} on:click={() => selectMainCategory("음료")}>음료</span>
                <span class:active={meal === "간식"} on:click={() => selectMainCategory("간식")}>간식</span>
                <span class:active={meal === "식사"} on:click={() => selectMainCategory("식사")}>식사</span>
                <span class:active={meal === "반찬"} on:click={() => selectMainCategory("반찬")}>반찬</span>
            </div>

            <div class="option last sub-category">
                {#each visibleSubCategories as subCategoryName}
                    <span class:active={category === subCategoryName} on:click={() => selectSubCategory(subCategoryName)}>{subCategoryName}</span>
                {/each}
            </div>
        </div>

        {#if !selectedMenu}
            <h2>대기중</h2>
            <button class="action-button" on:click={pickRandomMenu} disabled={isLoading}>
                {isLoading ? "로딩 중..." : "메뉴 뽑기"}
            </button>
        {:else}
            <h2>{selectedMenu.mname}</h2>
            <div>
                <button class="action-button" on:click={pickRandomMenu}>다시 뽑기</button>
                <button class="action-button yellow" on:click={findMenu}>식당 찾기</button>
                <button class="action-button orange" on:click={findRecipe}>레시피 찾기</button>
            </div>
        {/if}
    </div>
</main>

<style>
    .gacha {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
        user-select: none;
    }

    .img-container {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 400px;
        height: 400px; /* 가챠 전/후 동일한 높이로 설정 */
        background-color: #f9f9f9; /* 여백을 위한 배경색 */
        border: 1px solid #ddd;
        border-radius: 10px;
        margin-bottom: 20px;
    }

    .menu-image {
        width: 100%; /* 컨테이너 너비에 맞게 설정 */
        height: 100%; /* 컨테이너 높이에 맞게 설정 */
        border-radius: 10px; /* 둥근 모서리 유지 */
        object-fit: cover; /* 이미지가 컨테이너를 채우도록 설정 */
        display: block; /* 이미지를 블록 요소로 설정 */
    }

    .placeholder-text {
        position: absolute;
        font-size: 18px;
        color: #bbb;
        text-align: center;
    }

    .options {
        display: flex;
        width: 80%;
        flex-direction: column;
        margin-bottom: 20px;
        align-items: flex-start;
    }

    .option {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        justify-content: flex-start;
        font-size: 16px;
        cursor: pointer;
        border-top: 2px solid;
        width: 100%;
    }

    .last {
        border-bottom: 2px solid;
    }

    .option span {
        padding: 15px 20px;
        transition: all 0.2s ease;
        cursor: pointer;
        text-align: center;
    }

    .option span.active {
        background-color: #007bff;
        color: white;
        border-color: #0056b3;
    }

    h2 {
        display: flex;
        justify-content: center;
    }

    .action-button {
        margin-left: 10px;
        margin-right: 10px;
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 10px;
        cursor: pointer;
    }

    .action-button:hover {
        background-color: #0056b3;
    }
</style>
