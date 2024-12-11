<script>
    import { navigate } from "svelte-routing";

    let image = "/img/SequoiaLight.png";
    function editData() {
        console.log("Edit");
    }
    function deleteData() {
        console.log("Delete");
    }
</script>

<main class="main">
    <h1>Recipe</h1>

    <div class="element" on:click={() => navigate('/recipedetail')}>
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
    </div>
    <!-- --------------^형식으로 만들면 됨--------------- -->

    <!-- --------------테스트용--------------- -->
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
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

    span {
        cursor: pointer;
        user-select: none;
    }
</style>