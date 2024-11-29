<script>
    let image = "/img/SequoiaLight.png";
    let exImg = "/img/slide3.png";
    function deleteData() {
        console.log("Delete");
    }
    function navigateToGacha() {
        window.location.href = "/gacha";
    }

    //이거 고쳐야됨 History.java 만들어주셈
    let formData = {
        uid: "",
        mid: "",
        mname: "",
    };

    formData.uid = sessionStorage.getItem("idkey");

    let HistoryFood = [];

    async function getHistory() {
        try {
            const response = await fetch(
                "http://localhost:8080/api/history/all",
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
            HistoryFood = result;
            console.log("서버 응답", result);
        } catch (error) {
            console.error(`데이터 전송 오류`, error);
        }
    }

    getHistory();
</script>

<main class="main">
    <h1>History</h1>

    {#each HistoryFood as history}
        <div class="element">
            <img src={image} alt="Image" />
            <div class="stringBox">
                <span class="title">{history.name}</span>
                <div>
                    <span class="subtitle">Subtitle</span>
                    <span class="subtitle">Subtitle</span>
                </div>
            </div>
            <div class="contentBox">
                <span class="delete" on:click={() => deleteData()}>Delete</span>
            </div>
        </div>
    {/each}

    <!-- --------------형식으로 만들면 됨--------------- -->

    <!-- --------------테스트용--------------- -->
    <div class="element">
        <img src={exImg} alt="Image" />
        <div class="stringBox">
            <span class="title">고기고기고기고기</span>
            <div>
                <span class="subtitle">전체</span> -
                <span class="subtitle">한식</span> -
                <span class="subtitle">육류</span>
            </div>
        </div>
        <div class="contentBox">
            <span class="direct" on:click={navigateToGacha}>바로가기</span>
            <span class="delete" on:click={() => deleteData()}>Delete</span>
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

    .element .direct {
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
