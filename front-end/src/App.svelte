<script>
    $: foods = [];

    async function fetchFoods() {
        const response = await fetch('http://localhost:8081/api/foods',{
            method: "get",
        });
        foods = await response.json()
        console.log(foods)
    }

</script>

<main>
    <h1>오늘 무엇을 먹을까요?</h1>
    <button on:click={fetchFoods}>음식 리스트 가져오기</button>
    <ul>
        {#each foods as food}
            <li>{food}  ...<a href="https://www.10000recipe.com/recipe/list.html?q={food}">레시피 보기</a> ...<a href="https://map.naver.com/p/search/{food}">식당 찾기</a></li>
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
