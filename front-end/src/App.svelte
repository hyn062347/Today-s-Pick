<script>
    //$: foods = [];
    import { Router, Route, Link } from "svelte-routing";
    import Home from "./pages/Home.svelte";
    import About from "./pages/About.svelte";
    import Carana from "./pages/Carana.svelte";
    import Header from "./components/Header.svelte";
    import SignUp from "./pages/SignUp.svelte";
    import SignIn from "./pages/SignIn.svelte";
    import { comment } from "svelte/internal";
    import SelectPage from "./pages/SelectPage.svelte";
    // const link1="https://www.10000recipe.com/recipe/list.html?q="
    // const link2="https://map.naver.com/p/search/"
    // async function fetchFoods() {
    //     const response = await fetch('http://localhost:8081/api/foods',{
    //         method: "get",
    //     });
    //     foods = await response.json()
    //     console.log(foods)
    // }

</script>

<main>
    <!-- <h1>오늘 무엇을 먹을까요?</h1>
    <button on:click={fetchFoods}>음식 리스트 가져오기</button>
    <ul>
        {#each foods as food}
            <li>{food}  ...<a href="{link1}{food}">레시피 보기</a> ...<a href="{link2}{food}">식당 찾기</a></li>
        {/each}
    </ul>
    <p>기능이 쏙쏙 들어가있잖아aaas</p> -->

    <!-- <a href="/about">about</a> -->
    <!-- <a href="/carana">Canara</a> -->
    
    <!-- <nav>
        <Link to ="/">Home</Link>
        <Link to ="/about">About</Link>
        <Link to ="/carana">Carana</Link>
    </nav> -->
    <Header/>
    <Router>
        <Route path="/" component = {Home} />
        <Route path="/about" component = {About} />
        <Route path="/carana" component = {Carana}/>
        <Route path="/signup" component = {SignUp}/>
        <Route path="/signin" component = {SignIn}/>
        <Route path="/selectpage" component = {SelectPage}/>
    </Router>
</main>

<style>
    h1 {
        color: #d70000;
    }
    button {
        margin: 10px 0;
    }
    body {
        background-color: #333;
    }
</style>
