<script>
    let image = "/img/SequoiaLight.png";
    function editData() {
        console.log("Edit");
    }
    function deleteData() {
        console.log("Delete");
    }
</script>

<main class="main">
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
        <div class="contentBox">
            <span class="Edit" on:click={() => editData()}>Edit</span>
            <span class="delete" on:click={() => deleteData()}>Delete</span>
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
        <div class="contentBox">
            <span class="Edit" on:click={() => editData()}>Edit</span>
            <span class="delete" on:click={() => deleteData()}>Delete</span>
        </div>
    </div>
    <!-- --------------테스트용--------------- -->
</main>

<style>
    .main {
        padding: 5% 20%;
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
        border-bottom: 1px solid;
        padding-left: 10px;
        padding-right: 10px;
    }

    .element img {
        height: 70px;
        width: 70px;
        margin: 10px;
    }

    .element .delete {
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
        font-size: large;
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
