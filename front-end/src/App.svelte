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

    async function checkSession() {
        const response = await fetch('/api/account/session',{
            method: 'GET',
            credentials: 'include',
        });

        if(response.ok){
            const data = await response.json();
            user.set(data);
        }else{
            user.set(null);
        }
    }

    checkSession();
</script>

<main>
    {#if !sessionStorage.getItem("idkey")}
        <Header/>
    {:else}
        <HeaderUser/>
    {/if}
    <Router>
        <Route path="/" component = {Home} />
        <Route path="/about" component = {About} />
        <Route path="/carana" component = {Carana}/>
        <Route path="/signup" component = {SignUp}/>
        <Route path="/signin" component = {SignIn}/>
        <Route path="/gacha" component = {Gacha}/>
        <Route path="/favorite" component = {Favorite}/>
        <Route path="/mypage" component = {MyPage}/>
        <Route path="/myrecipe" component = {MyRecipe}/>
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
