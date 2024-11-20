<script>
    let mealTime = "전체"; // 아침, 점심, 저녁
    let cuisine = "전체"; // 한식, 양식, 중식, 일식
    let category = "전체"; // 밥류, 면류, 육류, 튀김류
    let selectedMenu = null; // 가챠로 선택된 메뉴

    // 임시 데이터 (DB 연동 예정)
    let menus = [
        { name: "치킨", mealTime: "저녁", cuisine: "한식", category: "육류", image: "/img/slide1.png" },
        { name: "스파게티", mealTime: "점심", cuisine: "양식", category: "면류", image: "/img/slide2.png" },
        { name: "초밥", mealTime: "점심", cuisine: "일식", category: "밥류", image: "/img/slide3.png" },
    ];

    // 메뉴 랜덤 뽑기
    function pickRandomMenu() {
        const filteredMenus = menus.filter(
            menu =>
                (mealTime === "전체" || menu.mealTime === mealTime) &&
                (cuisine === "전체" || menu.cuisine === cuisine) &&
                (category === "전체" || menu.category === category)
        );

        if (filteredMenus.length > 0) {
            selectedMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];
        } else {
            alert("조건에 맞는 메뉴가 없습니다. 다시 선택해주세요.");
        }
    }

    // 선택 초기화
    function resetSelection() {
        mealTime = "전체";
        cuisine = "전체";
        category = "전체";
        selectedMenu = null;
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
                    <span class:active={mealTime === "전체"} on:click={() => (mealTime = "전체")}>전체</span>
                    <span class:active={mealTime === "아침"} on:click={() => (mealTime = "아침")}>아침</span>
                    <span class:active={mealTime === "점심"} on:click={() => (mealTime = "점심")}>점심</span>
                    <span class:active={mealTime === "저녁"} on:click={() => (mealTime = "저녁")}>저녁</span>
                </div>
                <div class="option">
                    <span class:active={cuisine === "전체"} on:click={() => (cuisine = "전체")}>전체</span>
                    <span class:active={cuisine === "한식"} on:click={() => (cuisine = "한식")}>한식</span>
                    <span class:active={cuisine === "양식"} on:click={() => (cuisine = "양식")}>양식</span>
                    <span class:active={cuisine === "중식"} on:click={() => (cuisine = "중식")}>중식</span>
                    <span class:active={cuisine === "일식"} on:click={() => (cuisine = "일식")}>일식</span>
                </div>
                <div class="option">
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
            <div class="result">
                <h2>{selectedMenu.name}</h2>
                <div>
                    <button class="action-button" on:click={resetSelection}>다시 뽑기</button>
                    <button class="action-button yellow">식당 찾기</button>
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
        gap: 10px;
        margin-bottom: 20px;
    }

    .option {
        display: flex;
        justify-content: center;
        gap: 20px;
        font-size: 16px;
        cursor: pointer;
    }

    .option span {
        padding: 5px 10px;
        border: 1px solid transparent;
        border-radius: 5px;
        transition: all 0.2s ease;
    }

    .option span.active {
        background-color: #007bff;
        color: white;
        border-color: #0056b3;
    }

    .action-button {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
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
        gap: 20px;
    }
</style>
