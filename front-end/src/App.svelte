<script>
    $: foods = [];

    async function fetchFoods() {
        const response = await fetch('http://localhost:8081/api/foods',{
            method: "get",
        });
        foods = await response.json()
        console.log(foods)
    }

    // 페이지가 로드될 때 음식 데이터를 가져옵니다.
    fetchFoods();
</script>

<main>
    <h1>오늘 무엇을 먹을까요?</h1>
    <button on:click={fetchFoods}>음식 리스트 가져오기</button>
    <ul>
        {#each foods as food}
            <li>{food}</li>
        {/each}
    </ul>
</main>

<style>
    h1 {
        color: #333;
    }
    button {
        margin: 10px 0;
    }
</style>
