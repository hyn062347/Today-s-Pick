<script>
    export let name = "홈페이지";
</script>

<main>
    <div class="ButtonSection">
        <button >Button1</button>
        <button>Button2</button>
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
        border-radius: 15px;
        border: none;
        color: white;
        height: 30%;
        width: 30%;
        font-size: xx-large;
    }
</style>