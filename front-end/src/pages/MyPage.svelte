<script>
    let image = "/img/SequoiaLight.png";
</script>

<main class="setCenter">
    <div class="form-box">
        <form class="form">
            <span class="title">Profile</span>
            <img src={image} alt="Image" class="profileImage"/>
            <input type="text" class="input name" placeholder="Name"/>
            <div class="form-container">
                <input type="ID" class="input" placeholder="ID" />
                <input type="password" class="input" placeholder="Previous Password" />
                <input type="password" class="input" placeholder="New Password"/>
                <input type="email" class="input" placeholder="Email"/>
                <input type="text" class="input last" placeholder="Favorite"/>
            </div>
            <button type="submit">Confirm</button>
        </form>
    </div>
</main>

<style>
    .setCenter {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .profileImage{
    border-radius: 50%;
    height: 100px;
    width: 100px;
    margin-bottom: 10px;
    }

    .form-box {
        position: absolute;
        top: 30%;
        max-width: 300px;
        background: #f1f7fe;
        overflow: hidden;
        border-radius: 16px;
        color: #010101;
    }

    .form {
        position: relative;
        display: flex;
        flex-direction: column;
        padding: 32px 24px 24px;
        gap: 16px;
        text-align: center;
        align-items: center;
    }

    /*Form text*/
    .title {
        font-weight: bold;
        font-size: 1.6rem;
        display: flex;
        text-align: center;
        margin-bottom: 10px;
    }

    .subtitle {
        font-size: 1rem;
        color: #666;
    }

    /*Inputs box*/
    .form-container {
        overflow: hidden;
        border-radius: 8px;
        background-color: #fff;
        margin: 1rem 0 0.5rem;
        font-size: 0.9rem;
        width: 100%;
    }

    .input {
        background: none;
        border: 0;
        outline: 0;
        height: 40px;
        width: 100%;
        border-bottom: 1px solid #eee;
        padding: 8px 15px;
    }

    .name{
        font-size: 1.4rem;
        display: flex;
        text-align: center;
        margin-bottom: 0px;
    }

    .last{
        margin-bottom: 0px;
    }
    .form-section {
        padding: 16px;
        font-size: 0.85rem;
        background-color: #e0ecfb;
        box-shadow: rgb(0 0 0 / 8%) 0 -1px;
    }

    .form-section a {
        font-weight: bold;
        color: #0066ff;
        transition: color 0.3s ease;
    }

    .form-section a:hover {
        color: #005ce6;
        text-decoration: underline;
    }

    /*Button*/
    .form button {
        background-color: #0066ff;
        color: #fff;
        border: 0;
        border-radius: 15px;
        padding: 10px 16px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .form button:hover {
        background-color: #005ce6;
    }
</style>
