<script>
    let image = "/img/SequoiaLight.png";
    let exImg = "/img/slide3.png";

    function gotoMenu() {
        window.location.href='/gacha';
    }

    function deleteData() {
        console.log("Delete");
    }
</script>

<main class="main">
    <h1>Favorite</h1>



    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <div>
                <span class="subtitle">Subtitle</span>
                <span class="subtitle">Subtitle</span>
            </div>
        </div>
        <div class="contentBox">
            <span class="goto" on:click={gotoMenu}>바로가기</span>
            <span class="delete" on:click={deleteData}>삭제하기</span>
        </div>
    </div>
    <!-- --------------형식으로 만들면 됨--------------- -->

    <!-- --------------테스트용--------------- -->
    <div class="element">
        <img src={exImg} alt="Image" />
        <div class="stringBox">
            <span class="title">고기고기고기고기</span>
            <div>
                <span class="subtitle">전체</span> -
                <span class="subtitle">한식</span> -
                <span class="subtitle">육류</span>
            </div>
        </div>
        <div class="contentBox">
            <span class="goto" on:click={gotoMenu}>바로가기</span>
            <span class="delete" on:click={deleteData}>삭제하기</span>
        </div>
    </div>
    <!-- --------------테스트용--------------- -->
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

    .element .goto {
        font-size: 20px;
        color: #333;
    }

    .element .delete {
        font-size: 20px;
        color: red;
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
