<!DOCTYPE html>
<html>
<head>
    <title>Radio Button Example</title>
    <script src="radioButtonSubmitter.js"></script>
</head>
<body>
    <form method="post" id="radioForm">
        <input type="radio" id="buttonA" name="selection" value="A">
        <label for="buttonA">Button A</label><br>

        <input type="radio" id="buttonB" name="selection" value="B">
        <label for="buttonB">Button B</label><br>

        <input type="radio" id="buttonC" name="selection" value="C">
        <label for="buttonC">Button C</label><br>

        <input type="submit" value="Submit">
    </form>

    <div id="result"></div>
</body>
</html>
