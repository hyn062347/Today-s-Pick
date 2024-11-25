<script>
    let image = "/img/SequoiaLight.png";
    function editData() {
        console.log("Edit");
    }
    function deleteData() {
        console.log("Delete");
    }
    function navigateToUpload() {
        window.location.href = '/upload';
    }
</script>

<main class="main">

    <h1>MyRecipe</h1>

    <!-- ----------------------------- -->
    <div class="element">
        <img src={image} alt="Image" />
        <div class="stringBox">
            <span class="title">Title</span>
            <span class="subtitle">Subtitle</span>
        </div>
        <div class="contentBox">
            <span class="edit" on:click={() => editData()}>Edit</span>
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
            <span class="edit" on:click={() => editData()}>Edit</span>
            <span class="delete" on:click={() => deleteData()}>Delete</span>
        </div>
    </div>
    <!-- --------------테스트용--------------- -->
     <div class="element">
        <span class="upload" on:click={navigateToUpload}>등록하기</span>
     </div>
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

    .element .edit {
        font-size: 20px;
    }

    .element .delete {
        font-size: 20px;
        color: red;
    }

    .element .upload {
        display: flex;
        flex: 1;
        align-items: center;
        justify-content: center;
        height: 130px;
        font-size: 30px;
        font-weight: bold;
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