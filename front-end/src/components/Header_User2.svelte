<script>
    function navigateToHome() {
        window.location.href = '/';
    }

    function uploadRecipe() {
        window.location.href='/upload';
    }
</script>

<header class="HeaderForm">
    <div>
        <!-- <a href="/" class="Logo">Logo</a> -->
    </div>
    <label class="Title" on:click={navigateToHome}>오늘 뭐먹지?</label>
    <div class="ButtonSection">
        <button class="UploadButton" on:click={uploadRecipe}>레시피 등록</button>
    </div>
</header>

<style>
    .Logo{
        color: white;
        font-weight: bolder;
        font-size: large;
        margin-left: 10px;
        user-select: none;
    }

    .HeaderForm {
        background-color: rgb(0, 123, 255);
        height: 60px;
        width: 100%;
        text-align: center;
        align-content: center;
        color: white;
        align-items: center;
        display: flex;
        flex-direction: row;
        justify-content: space-between;        
    }

    .UploadButton {
        background-color: rgb(38, 38, 38);
        padding-left: 15px;
        padding-right: 15px;
    }

    button {
        margin: 0px;
        border-radius: 10px;
        color: white;
        border: none;
    }

    .ButtonSection{
        margin-left: 10px;
        margin-right: 10px;
    }

    .Title {
        font-size: 30px;
        font-weight: bolder;
        position: absolute; /* 절대 위치 추가 */
        left: 43%; /* 가로 중앙 */
        cursor: pointer; /* 클릭 가능 표시 */
        user-select: none; /* 텍스트 선택 방지 */
    }
</style>
