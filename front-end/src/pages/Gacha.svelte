<script>
    let meal = "음료"; // 음료, 간식, 식사
    let category = "전체"; // 전체, 밥류, 면류, 육류, 튀김류 등
    let selectedMenu = null; // 가챠로 선택된 메뉴
    let photoUrl = ""; // Flickr에서 가져온 사진 URL
    const apiKey = "ee561aac775d80987baa6fa0ce000255"; // Flickr API Key

    const subCategories = {
        음료: ["전체", "음료 및 차류", "유제품류 및 빙과류"],
        간식: ["전체", "빵 및 과자류", "젓갈류", "장아찌·절임류"],
        식사: ["전체", "밥류", "면류", "육류", "튀김류", "국 및 탕류", "찌개 및 전골류", "조림류", "구이류", "나물·숙채류",],
        기타: ["전체", "곡류, 서류 제품", "과일류", "김치류", "두류, 견과 및 종실류", "생채·무침류", "수·조·어·육류", "죽 및 스프류", "전·적 및 부침류", "장류, 양념류",],
    };

    let visibleSubCategories = subCategories["음료"]; // 초기값

    let menus = [
        { name: "후라이드치킨", meal: "식사", category: "구이류", image: "/img/slide1.png" },
        { name: "스파게티", meal: "식사", category: "튀김류", image: "/img/slide2.png" },
        { name: "초밥", meal: "식사", category: "밥류", image: "/img/slide3.png" },
    ];

    function selectMainCategory(category) {
        meal = category;
        visibleSubCategories = subCategories[category];
        category = "전체"; // 서브 카테고리 초기화
    }

    function selectSubCategory(subCategoryName) {
        category = subCategoryName;
    }

    async function pickRandomMenu() {
        const filteredMenus = menus.filter(
            (menu) =>
                (meal === "전체" || menu.meal === meal) &&
                (category === "전체" || menu.category === category)
        );

        if (filteredMenus.length > 0) {
            selectedMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];
            const searchKeyword = selectedMenu.name.split("_")[0]; // '_' 뒤 단어 제외
            await fetchPhoto(searchKeyword); // Flickr API로 사진 검색
        } else {
            alert("조건에 맞는 메뉴가 없습니다. 다시 선택해주세요.");
        }
    }

    async function fetchPhoto(keyword) {
    try {
        const response = await fetch(
        `https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=${apiKey}&text=${encodeURIComponent(keyword)}&sort=interestingness-desc&content_type=1&per_page=1&format=json&nojsoncallback=1`
        );

        const data = await response.json();
        if (data.photos.photo.length > 0) {
            const photo = data.photos.photo[0];
            photoUrl = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg`;
        } else {
            photoUrl = ""; // 사진이 없으면 기본 이미지
        }
    } catch (error) {
        console.error("Flickr API 오류:", error);
        photoUrl = ""; // 에러 발생 시 기본 이미지
    }
}


    function findMenu() {
        if (!selectedMenu) {
            alert("메뉴가 선택되지 않았습니다. 메뉴를 먼저 뽑아주세요.");
            return;
        }

        const link = `https://map.naver.com/p/search/${encodeURIComponent(selectedMenu.name)}`;
        window.open(link, "_blank");
    }
    function findRecipe() {
        window.location.href='/recipe';
        window.open(link, "_blank");
    }
</script>

<main>
    <div class="gacha">
        <div class="img-container">
            {#if selectedMenu}
                <img src={photoUrl} alt={selectedMenu.name} class="menu-image" />
            {:else}
                <span class="placeholder-text">메뉴를 선택해주세요!</span>
            {/if}
        </div>

        <div class="options">
            <div class="option">
                <span class:active={meal === "음료"} on:click={() => selectMainCategory("음료")}>음료</span>
                <span class:active={meal === "간식"} on:click={() => selectMainCategory("간식")}>간식</span>
                <span class:active={meal === "식사"} on:click={() => selectMainCategory("식사")}>식사</span>
                <span class:active={meal === "기타"} on:click={() => selectMainCategory("기타")}>기타</span>
            </div>

            <div class="option last sub-category">
                {#each visibleSubCategories as subCategoryName}
                    <span class:active={category === subCategoryName} on:click={() => selectSubCategory(subCategoryName)}>{subCategoryName}</span>
                {/each}
            </div>
        </div>

        {#if !selectedMenu}
            <h2>대기중</h2>
            <button class="action-button" on:click={pickRandomMenu}>메뉴 뽑기</button>
        {:else}
            <h2>{selectedMenu.name}</h2>
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
