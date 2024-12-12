<script>
    import { navigate } from "svelte-routing";

    let image = "/img/SequoiaLight.png";

    let recipes = [
        {
            image: '/img/noImg.png',        // 이미지 파일
            menu: '메뉴이름',           // 메뉴 이름
            title: '레시피 제목1',          // 레시피 제목
            category: '카테고리1',       // 분류
            uid: 'GPT'      // 작성자 UID (임시 값)
        },
        {
            image: '/img/noImg.png',        // 이미지 파일
            menu: '메뉴이름',           // 메뉴 이름
            title: '레시피 제목2',          // 레시피 제목
            category: '카테고리2',       // 분류
            uid: 'GPT'      // 작성자 UID (임시 값)
        },
        {
            image: '/img/noImg.png',        // 이미지 파일
            menu: '메뉴이름',           // 메뉴 이름
            title: '레시피 제목3',          // 레시피 제목
            category: '카테고리3',       // 분류
            uid: 'GPT'      // 작성자 UID (임시 값)
        },
    ]

</script>

<main class="main">
    <h1>Recipe</h1>
    {#each recipes as recipe (recipe.title)}
        <div class="element" on:click={() => navigate('/recipedetail')}>
            <img src={recipe.image} alt="Image" />
            <div class="stringBox">
                <span class="title">{recipe.title}</span>
                <span class="subtitle">{recipe.menu}, {recipe.category}</span>
                <span class="subtitle">작성자 - {recipe.uid}</span>
            </div>
        </div>
    {/each}
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
        color: #a9a9a9;
        font-size: 16px;
        font-family: 'Inter';
    }

    span {
        cursor: pointer;
        user-select: none;
    }
</style>