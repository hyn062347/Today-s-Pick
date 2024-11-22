<script>
    let meal = "전체"; // 식사, 간식, 디저트
    let cuisine = "전체"; // 한식, 양식, 중식, 일식
    let category = "전체"; // 밥류, 면류, 육류, 튀김류
    let selectedMenu = null; // 가챠로 선택된 메뉴

    // 임시 데이터 (DB 연동 예정)
    let menus = [
        { name: "치킨", meal: "식사", cuisine: "한식", category: "육류", image: "/img/slide1.png" },
        { name: "스파게티", meal: "식사", cuisine: "양식", category: "면류", image: "/img/slide2.png" },
        { name: "초밥", meal: "식사", cuisine: "일식", category: "밥류", image: "/img/slide3.png" },
    ];

    // 메뉴 랜덤 뽑기
    function pickRandomMenu() {
        const filteredMenus = menus.filter(
            menu =>
                (meal === "전체" || menu.meal === meal) &&
                (cuisine === "전체" || menu.cuisine === cuisine) &&
                (category === "전체" || menu.category === category)
        );

        if (filteredMenus.length > 0) {
            selectedMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];
        } else {
            alert("조건에 맞는 메뉴가 없습니다. 다시 선택해주세요.");
        }
    }

    // 식당 찾기
    function findMenu() {
    if (!selectedMenu) {
        alert("메뉴가 선택되지 않았습니다. 메뉴를 먼저 뽑아주세요.");
        return;
    }

    const link = `https://map.naver.com/p/search/${encodeURIComponent(selectedMenu.name)}`;
    window.open(link, "_blank"); // 새 탭에서 열기
}
</script>

<main>
    <div class="gacha">
        {#if !selectedMenu}
            <!-- 메뉴 선택 화면 -->
            <div class="img-container">
                <!-- 가챠 전: 이미지 없음, 비어 있는 여백 -->
            </div>
            <div class="options">
                <div class="option">
                    <span class:active={meal === "전체"} on:click={() => (meal = "전체")}>전체</span>
                    <span class:active={meal === "식사"} on:click={() => (meal = "식사")}>식사</span>
                    <span class:active={meal === "간식"} on:click={() => (meal = "간식")}>간식</span>
                    <span class:active={meal === "디저트"} on:click={() => (meal = "디저트")}>디저트</span>
                </div>
                <div class="option">
                    <span class:active={cuisine === "전체"} on:click={() => (cuisine = "전체")}>전체</span>
                    <span class:active={cuisine === "한식"} on:click={() => (cuisine = "한식")}>한식</span>
                    <span class:active={cuisine === "양식"} on:click={() => (cuisine = "양식")}>양식</span>
                    <span class:active={cuisine === "중식"} on:click={() => (cuisine = "중식")}>중식</span>
                    <span class:active={cuisine === "일식"} on:click={() => (cuisine = "일식")}>일식</span>
                </div>
                <div class="option last">
                    <span class:active={category === "전체"} on:click={() => (category = "전체")}>전체</span>
                    <span class:active={category === "밥류"} on:click={() => (category = "밥류")}>밥류</span>
                    <span class:active={category === "면류"} on:click={() => (category = "면류")}>면류</span>
                    <span class:active={category === "육류"} on:click={() => (category = "육류")}>육류</span>
                    <span class:active={category === "튀김류"} on:click={() => (category = "튀김류")}>튀김류</span>
                </div>
            </div>
            <button class="action-button" on:click={pickRandomMenu}>메뉴 뽑기</button>
        {:else}
            <!-- 메뉴 결과 화면 -->
            <div class="img-container">
                <!-- 가챠 후: 메뉴 이미지 표시 -->
                <img src={selectedMenu.image} alt={selectedMenu.name} class="menu-image" />
            </div>
            <div class="options">
                <div class="option">
                    <span class:active={meal === "전체"} on:click={() => (meal = "전체")}>전체</span>
                    <span class:active={meal === "식사"} on:click={() => (meal = "식사")}>식사</span>
                    <span class:active={meal === "간식"} on:click={() => (meal = "간식")}>간식</span>
                    <span class:active={meal === "디저트"} on:click={() => (meal = "디저트")}>디저트</span>
                </div>
                <div class="option">
                    <span class:active={cuisine === "전체"} on:click={() => (cuisine = "전체")}>전체</span>
                    <span class:active={cuisine === "한식"} on:click={() => (cuisine = "한식")}>한식</span>
                    <span class:active={cuisine === "양식"} on:click={() => (cuisine = "양식")}>양식</span>
                    <span class:active={cuisine === "중식"} on:click={() => (cuisine = "중식")}>중식</span>
                    <span class:active={cuisine === "일식"} on:click={() => (cuisine = "일식")}>일식</span>
                </div>
                <div class="option last">
                    <span class:active={category === "전체"} on:click={() => (category = "전체")}>전체</span>
                    <span class:active={category === "밥류"} on:click={() => (category = "밥류")}>밥류</span>
                    <span class:active={category === "면류"} on:click={() => (category = "면류")}>면류</span>
                    <span class:active={category === "육류"} on:click={() => (category = "육류")}>육류</span>
                    <span class:active={category === "튀김류"} on:click={() => (category = "튀김류")}>튀김류</span>
                </div>
            </div>
            <div class="result">
                <h2>{selectedMenu.name}</h2>
                <div>
                    <button class="action-button" on:click={pickRandomMenu}>다시 뽑기</button>
                    <button class="action-button yellow" on:click={findMenu}>식당 찾기</button>
                    <button class="action-button orange">레시피 찾기</button>
                </div>
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
        width: 100%;
        height: 100%;
        border: 1px solid #ddd;
        border-radius: 10px;
        object-fit: cover;
    }

    .options {
        display: flex;
        flex-direction: column;
        margin-bottom: 20px;
        align-items: flex-start;
    }

    .option {
        display: grid;
        grid-template-columns: repeat(5, 1fr);
        justify-content: flex-start;
        font-size: 16px;
        cursor: pointer;
        border-top: 2px solid;
        width: 100%;
    }

    .last{
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

    .action-button.yellow {
        background-color: #ffc107;
    }

    .action-button.orange {
        background-color: #fd7e14;
    }

    .action-button.yellow:hover {
        background-color: #e0a800;
    }

    .action-button.orange:hover {
        background-color: #e8590c;
    }

    .result {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 0px;
    }
</style>
