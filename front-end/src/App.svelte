<script>
    //$: foods = [];
    import { Router, Route, Link } from "svelte-routing";
    import { user } from './store';
    import Home from "./pages/Home.svelte";
    import About from "./pages/About.svelte";
    import Carana from "./pages/Carana.svelte";
    import Header from "./components/Header.svelte";
    import SignUp from "./pages/SignUp.svelte";
    import SignIn from "./pages/SignIn.svelte";
    import Gacha from "./pages/Gacha.svelte";
    import Favorite from "./pages/Favorite.svelte";
    import MyPage from "./pages/MyPage.svelte";
    import MyRecipe from "./pages/MyRecipe.svelte";
    import HeaderUser from "./components/Header_User.svelte";
    import Upload from "./pages/Upload.svelte";
    import Recipe from "./pages/Recipe.svelte";
    import History from "./pages/History.svelte";
    let currentPath = "/";

</script>

<main>
    {#if !sessionStorage.getItem("idkey")}
        <Header/>
    {:else}
        <HeaderUser />
    {/if}
    <Router on:routeEvent={(e) => (currentPath = e.detail.location.pathname)}>
        <Route path="/" component = {Home} />
        <Route path="/about" component = {About} />
        <Route path="/carana" component = {Carana}/>
        <Route path="/signup" component = {SignUp}/>
        <Route path="/signin" component = {SignIn}/>
        <Route path="/gacha" component = {Gacha}/>
        <Route path="/favorite" component = {Favorite}/>
        <Route path="/mypage" component = {MyPage}/>
        <Route path="/myrecipe" component = {MyRecipe}/>
        <Route path="/upload" component = {Upload}/>
        <Route path="/recipe" component = {Recipe}/>
        <Route path="/history" component = {History}/>
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
