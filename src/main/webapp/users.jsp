<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Page d'inscription - DIGIT DOCS</title>
   <style>
    body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 50px;
  background-color: rgb(212, 209, 216);
  background-image: url('Image\ collée\ \(3\).png');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  background-attachment: fixed;
}

form {
  background-color: rgba(176, 176, 184, 0.7);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 40px rgba(0,0,0,0.1);
  width: 650px;
  max-width: 90%;
  margin-left: 0; /* on garde le formulaire collé au bord gauche du cadre */
}



h1 {
  text-align: center;
  color: #050000;
}
label {
  display: block;
  margin-top: 15px;
  margin-bottom: 5px;
  font-weight: bold;
}
input, select, button {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ccc;
  font-size: 1em;
}
button {
  margin-top: 20px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
  font-weight: bold;
}
button:hover {
  background-color: #0056b3;
}
#adminCodeDiv {
  display: none;
}
.test{
    position: left;
}

.container {
  display: flex;
  gap: 60px; /* espace entre les colonnes */
}



.logo {
  
   width: 60px; /* Taille du logo */
    height: 60px;
    background-image: url("Image\ collée.png");
    background-size: contain; /* Ajuste le logo sans le déformer */
    background-repeat: no-repeat;
    background-position: center;
    margin-right: 15px; /* Espace entre le logo et le texte */
}

.description {
  font-size: 14px;
  color: #020000;
  line-height: 1.6;
}

.right-panel form {
  background-color: rgba(255, 255, 255, 0.7);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 40px rgba(0,0,0,0.1);
  width: 650px;
  max-width: 90%;
}





.titre{
  color: #02172e;
}


.titre-principal {
    text-align: center;
    margin-top: 50px;
}

.titre-principal h1 {
    font-size: 2.2em;
    font-weight: bold;
    margin-bottom: 15px;
    color: #333;
}

.titre-principal .description {
    font-size: 1.1em;
    line-height: 1.6;
    color: #555;
    max-width: 700px;
    margin: 0 auto;
}

/* --- En-tête --- */
header {
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 10px 20px;
    background-color: #f8f8f8;
    border-bottom: 2px solid #ddd;
}

.logo {
    width: 60px;
    height: 60px;
    background-image: url("Image collée.png"); /* Chemin vers ton logo */
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    position: relative;
    top: -5px;
}

.header-text {
    display: flex;
    flex-direction: column;
}

.brand-name {
    margin: 0;
    font-size: 1.8em;
    font-weight: bold;
    color: #02172e;
}

.subtitle {
    margin: 0;
    font-size: 1em;
    color: #333;
}

/* --- Bouton S'inscrire --- */
.btn-inscrire {
    display: inline-block;
    padding: 12px 25px;
    margin-top: 25px;
    background: linear-gradient(45deg, #007bff, #0056b3);
    color: white;
    text-decoration: none;
    font-weight: bold;
    border-radius: 8px;
    box-shadow: 0px 4px 8px rgba(0,0,0,0.2);
    transition: all 0.3s ease;
}

.btn-inscrire:hover {
    background: linear-gradient(45deg, #0056b3, #004494);
    transform: translateY(-2px);
    box-shadow: 0px 6px 12px rgba(0,0,0,0.3);
}

/* --- Responsive tablette --- */
@media screen and (max-width: 768px) {
    header {
        flex-direction: column;
        align-items: center;
        text-align: center;
        gap: 8px;
    }

    .logo {
        width: 50px;
        height: 50px;
        top: 0;
    }

    .brand-name {
        font-size: 1.5em;
    }

    .subtitle {
        font-size: 0.9em;
    }

    .btn-inscrire {
        padding: 10px 20px;
        margin-top: 15px;
        font-size: 0.95em;
        ;
    }
}

/* --- Responsive téléphone --- */
@media screen and (max-width: 480px) {
    .brand-name {
        font-size: 1.3em;
    }

    .subtitle {
        font-size: 0.85em;
    }

    .btn-inscrire {
        width: 100%;
        max-width: 250px;
        text-align: center;
        padding: 10px;
    }
}

   </style>
   
</head>
<body>
  <div class="container">
    <!-- Colonne gauche -->
    <header>
      <div class="logo"></div>
      <div class="header-text">
        <h2 class="brand-name">DIGIT DOCS</h2>
        <div class="titre-principal">
          <h1>Ma plateforme d'authentification</h1>
          <p class="description">
            DIGIT docs est une plateforme qui vous permet de faciliter l'authentification
            des documents administratifs dans une entreprise. Un QR code est généré pour chaque
            document validé, et vos documents sont classés dans une bibliothèque pour une bonne organisation.
          </p>
        </div>
      </div>
    </header>

    <!-- Colonne droite -->
    <div class="right-panel">
      <h1>Inscription</h1>
      <form id="registrationForm" action="<%= request.getContextPath() %>/inscription" method="POST">
        <label for="username">Nom d'utilisateur :</label>
        <input type="text" id="username" name="username" required />

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required />

        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required />

        <label for="confirm_password">Confirmer le mot de passe :</label>
        <input type="password" id="confirm_password" name="confirm_password" required />

        <label for="role">Vous êtes :</label>
        <select id="role" name="role" required onchange="toggleAdminCode()">
          <option value="personnel">Personnel</option>
          <option value="admin">Administrateur</option>
        </select>

        <div id="adminCodeDiv" style="display:none;">
          <label for="adminCode">Code administratif :</label>
          <input type="password" id="adminCode" name="adminCode" />
        </div>

        <br><br>
        <button type="submit" class="btn-inscrire">S'inscrire</button>
      </form>
    </div>
  </div>

  <script>
    // Afficher ou masquer le champ Code administratif
    function toggleAdminCode() {
      const role = document.getElementById('role').value;
      document.getElementById('adminCodeDiv').style.display = role === 'admin' ? 'block' : 'none';
    }
    window.onload = toggleAdminCode; // applique au chargement

    // Gestion de la soumission et redirection selon le rôle
    document.getElementById('registrationForm').addEventListener('submit', function(e) {
      e.preventDefault(); // empêche la soumission classique

      const role = document.getElementById('role').value;
      const contextPath = '<%= request.getContextPath() %>';

      // Ici tu peux ajouter l'envoi au serveur via fetch/AJAX si nécessaire
      // Exemple simplifié : redirection après soumission
      if(role === 'admin'){
        window.location.href = contextPath + '/admin-dashboard';
      } else {
        window.location.href = contextPath + '/scanner';
      }

      // Si tu veux envoyer les données avec fetch, tu peux remplacer cette partie
    });
  </script>
</body>
</html>
