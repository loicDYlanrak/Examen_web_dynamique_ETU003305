<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de bord - Gestion financiere</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Tableau de bord - Lignes de credit et depenses</h2>

        <table class="table table-striped"> 
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ligne de credit</th>
                    <th>Montant credit (Ar)</th>
                    <th>Somme des depenses (Ar)</th>
                    <th>Reste (Ar)</th>
                </tr>
            </thead>
            <tbody>
                <%@ page import="java.util.List, service.LigneCreditService.CreditDepenseSummary" %>
                <% 
                    List<CreditDepenseSummary> summaryList = (List<CreditDepenseSummary>) request.getAttribute("summaryList");
                    if (summaryList != null) {
                        for (CreditDepenseSummary summary : summaryList) {
                %>
                <tr>
                    <td><%= summary.getId() %></td>
                    <td><%= summary.getLibelle() %></td>
                    <td><%= String.format("%,.2f", summary.getMontantCredit()) %></td>
                    <td><%= String.format("%,.2f", summary.getSommeDepenses()) %></td>
                    <td><%= String.format("%,.2f", summary.getReste()) %></td>
                </tr>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>

        <br>
        <a href="LigneCreditServlet"><button class="btn btn-primary">Ajouter une ligne de credit</button></a>
        <a href="LigneDepenseServlet"><button class="btn btn-primary">Ajouter une ligne de depense</button></a>
    </div>
    <div >
        <% 
            String error = (String) request.getAttribute("message");
            if (error != null) { 
            %>
                <div class="alert mt-3 text-center" role="alert">
                    <%= error %>
                </div>
            <% } %>
    </div>
    <script src="/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>