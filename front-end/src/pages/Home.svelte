<script>
    import Slider from "../components/Slider.svelte";
    import { navigate } from "svelte-routing";
    import { user } from '../store';
    export let name = "홈페이지";
</script>

<main>
    <Slider/>
    <div class="ButtonSection">
        {#if !sessionStorage.getItem("idkey")}
            
            <h1>Did not Log in</h1>
        {:else}
        <h1>Welcom, {sessionStorage.getItem("idkey")}</h1>
        {/if}
        <button class="TodaysPick" on:click={() => navigate("/gacha")}>Button1</button>
        <button class="Favorite" on:click={() => navigate("/favorite")}>즐겨찾기</button>
    </div>
</main>

<style>
    h1{
        color: aqua;
    }
    
    .ButtonSection{
        display: flex;
        flex-direction: row;
        /* align-content: center; */
        justify-content: space-evenly;
        align-items: center;
        padding: 60px;
    }

    button{
        border-radius: 20px;
        border: none;
        color: white;
        padding-left: 10%;
        padding-right: 10%;
        padding-top: 6%;
        padding-bottom: 6%;
        font-size: xx-large;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .Favorite{
        background-color: rgb(0, 145, 255);
    }

    .TodaysPick{
        background-color: blueviolet;
    }
</style>