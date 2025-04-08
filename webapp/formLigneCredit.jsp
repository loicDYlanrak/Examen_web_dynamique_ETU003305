<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des lignes de credit</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Ajouter une ligne de credit</h2>

        <form action="LigneCreditServlet" method="post">
            <div class="mb-3">
                <label for="libelle" class="form-label">Libelle</label>
                <input type="text" class="form-control" id="libelle" name="libelle" required>
            </div>
            <div class="mb-3">
                <label for="montant" class="form-label">Montant</label>
                <input type="number" step="0.01" class="form-control" id="montant" name="montant" required>
            </div>
            <button type="submit" class="btn btn-primary">Valider</button>
        </form>

        <br><br>
        <!-- <a href="LigneCreditServlet"><button class="btn btn-primary">Liste des lignes de credit</button></a> -->
        <br><br>
        <a href="LigneDepenseServlet"><button class="btn btn-primary">Ajouter une ligne de depense</button></a>
    </div>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>