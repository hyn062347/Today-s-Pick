<script>
    import Option from "./Option.svelte";
    let isOpen = false;

    function navigateToHome() {
        window.location.href = '/';
    }

    function toggleOption() {
        isOpen = !isOpen;
    }
</script>

<header class="HeaderForm">
    <div>
        <!-- <a href="/" class="Logo">Logo</a> -->
    </div>
    <label class="Title" on:click={navigateToHome}>오늘 뭐먹지?</label>
    <div class="ButtonSection">
        <img src="/img/user.png" alt="Profile" on:click={toggleOption}/>
        {#if isOpen}
            <Option />
        {/if}
    </div>
</header>

<main></main>

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
    
    .ButtonSection{
        margin-left: 10px;
        margin-right: 15px;
        position: relative;
    }

    .ButtonSection img{
        height: 40px;
        width: 40px;
        cursor: pointer;
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
