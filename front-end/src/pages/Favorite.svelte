<script>
    let image = "/img/SequoiaLight.png";
</script>

<main class="main">
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
        <span class="delete">Delete</span>
    </div>
<!-- --------------^형식으로 만들면 됨--------------- -->

<!-- --------------테스트용--------------- -->
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
        <span class="delete">Delete</span>
    </div>
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
        <span class="delete">Delete</span>
    </div>
<!-- --------------테스트용--------------- -->


</main>

<style>
    .main {
        padding: 5% 20%;
        display: flex;
        gap: 20px;
        flex-direction: column;
        justify-content: center;
    }

    .element {
        display: flex;
        flex-direction: row;
        gap: 20px;
        align-items: center;
        justify-content: space-between;
    }

    .element img {
        height: 80px;
        width: 80px;
    }

    .element .delete {
        color: red;
    }

    .stringBox {
        display: flex;
        flex-direction: column;
        flex: 1;
    }

    .stringBox .title {
        font-size: x-large;
        font-weight: bolder;
    }

    .stringBox .subtitle {
        font-size: large;
    }
</style>
