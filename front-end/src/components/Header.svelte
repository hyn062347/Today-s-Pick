<script>
    let currentPage = 0;
    import { Router, Route, Link } from "svelte-routing";
</script>

<header class="HeaderForm">
    <div>
        logo
    </div>
    <label class="Title">오늘 뭐먹지?</label>
    <div class="ButtonSection">
        <button class="LoginButton">Login</button>
        <button class="RegisterButton">Register</button>
    </div>
</header>

<main></main>

<style>

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
    .LoginButton {
        background-color: rgb(38, 38, 38);
        padding-left: 15px;
        padding-right: 15px;
    }

    .RegisterButton {
        background-color: rgb(112, 112, 112);
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
    }
</style>
