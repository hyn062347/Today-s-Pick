<script>

    let formData = {
        uid: "",
        rid: "",
    };

    formData.uid = sessionStorage.getItem("idkey");

    let favoriteFoods = [];
    function gotoRecipe(rid) {
        window.location.href = `/recipedetail?rid=${rid}`;
    }

    async function deleteData(rid) {
        formData.rid=rid;
        formData.uid=sessionStorage.getItem("idkey");
        try {
            const response = await fetch(
                "http://localhost:8080/api/favorite/delete",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(formData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            console.log("서버 응답", result);
        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
    }

    async function getFavorite() {
        try {
            const response = await fetch(
                "http://localhost:8080/api/favorite/all",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(formData),
                    credentials: "include",
                },
            );

            if (!response.ok) {
                throw new Error(`서버오류: ${response.status}`);
            }

            const result = await response.json();
            favoriteFoods = result;
            console.log("서버 응답", result);
        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
    }

    getFavorite();
</script>

<main class="main">
    <h1>Favorite</h1>

    <ul>
        {#each favoriteFoods as favorite}
            <li class="element">
                <img src="{favorite.rimg_src}{favorite.rimg_name}" alt="Image" />
                <div class="stringBox" on:click={gotoRecipe(favorite.rid)}>
                    <span class="title">{favorite.recipe_title}</span>
                    <div>
                        <span class="subtitle">{favorite.ctg}, {favorite.mname}</span>
                    </div>
                </div>
                <div class="contentBox">
                    <span class="delete" on:click={deleteData(favorite.rid)}>삭제하기</span>
                </div>
            </li>

        {/each}
    </ul>
</main>

<style>
    .main {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
    }

    ul{
        padding: 0px;
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

    .element .goto {
        font-size: 20px;
        color: #333;
    }

    .element .delete {
        font-size: 20px;
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
        color: #a9a9a9;
        font-size: 16px;
        font-family: "Inter";
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
