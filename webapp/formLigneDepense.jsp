<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des lignes de depense</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Ajouter une ligne de depense</h2>

        <form action="LigneDepenseServlet" method="post">
            <div class="mb-3">
                <label for="idCredit" class="form-label">Ligne de credit</label>
                <select class="form-select" id="idCredit" name="idCredit" required>
                    <option value="">Selectionner une ligne de credit</option>
                    <%@ page import="java.util.List, dep.LigneCredit" %>
                    <% 
                        List<LigneCredit> lignesCredit = (List<LigneCredit>) request.getAttribute("lignesCredit");
                        if (lignesCredit != null) {
                            for (LigneCredit lc : lignesCredit) {
                    %>
                        <option value="<%= lc.getId() %>"><%= lc.getLibelle() %> (Montant: <%= lc.getMontant() %>)</option>
                    <% 
                            }
                        }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label for="montant" class="form-label">Montant</label>
                <input type="number" step="0.01" class="form-control" id="montant" name="montant" required>
            </div>
            <button type="submit" class="btn btn-primary">Valider</button>
        </form>

        <br><br>
        <a href="LigneCreditServlet"><button class="btn btn-primary">Retour aux lignes de credit</button></a>
    </div>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>