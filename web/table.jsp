<%-- 
    Document   : table
    Created on : Apr 25, 2010, 6:42:58 PM
    Author     : art-jackal
--%>
<jsp:useBean id="cardBean" class="model.CardBean" scope="session"/>
<jsp:useBean id="UserBean" class="model.UserBean" scope="session"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
	<head>
		<title>Memory</title>
		<link rel="stylesheet" type="text/css" href="styles/screen.css" />
		<meta http-equiv="Content-type" content='text/html; charset="UTF-8"'/>
	</head>
	<body>
		<div id="header">
			<img src="img/header_left_v2.png" alt="Logo der Lehrveranstaltung EWA" />
		</div>
		<div id="navigation_container">
			<ul id="navigation">
				<li>|&nbsp;Userdaten &auml;ndern</li>
				<li>&nbsp;|&nbsp;Zur&uuml;ck zur Lounge</li>
				<li>&nbsp;|&nbsp;Ausloggen</li>
			</ul>
		</div>
		<div id="content">
			<div id="right-box">
				<h1 class="hidden">Table</h1>
				<h2>Spielstand</h2>
				<table id="score" summary="Die folgende Tabelle listet die Spielstaende auf!">
					<tr>
						<th>&nbsp;</th>
						<th>Du</th>
						<th>Gegner</th>
					</tr>
					<tr>
						<th class="tablehead">Paare</th>
						<td>1</td>
						<td>0</td></tr>
					<tr>
						<th class="tablehead">Versuche</th>
						<td>3</td>
						<td>2</td>
					</tr>
					<tr>
						<th class="tablehead">Zeit</th>
						<td>0:34</td>
						<td>0:50</td>
					</tr>
				</table>
				<h2>Spielinformationen</h2>
				<table id="gameinfo" summary="Die folgende Tabelle listet die Spielinformationen auf!">
					<tr>
						<th>Restliche Paare</th>
						<td>12</td>
					</tr>
					<tr>
						<th>Thema</th>
						<td>Alternative Rock</td>
					</tr>
				</table>
				<h2>Top-Künstler</h2>
				<p>
					<a href="#"><img src="img/muse.jpg" alt="Bild der Alternative Band Muse" />&nbsp;Muse</a>
				</p>
				<p>
					<a href="#"><img src="img/placebo.jpg" alt="Bild der Alternative Band Placebo" />&nbsp;Placebo</a>
				</p>					<p>
					<a href="#"><img src="img/radiohead.png" alt="Bild der Alternative Band Radiohead" />&nbsp;Radiohead</a>
				</p>
				<p>
					<a href="#"><img src="img/incubus.jpeg" alt="Bild der Alternative Band Incubus" />&nbsp;Incubus</a>
				</p>
				<p>
					<a href="#"><img src="img/foo_fighters.jpeg" alt="Bild der Alternative Band Foo Fighters" />&nbsp;Foo-Fighters</a>
				</p>
			</div>
                    <div id="card-box"><form name="spielbrett" action="POST">
				<div>
                                    <input type="image" src="img/card_background.png" alt="Eine nicht aufgedeckte Karte"/>
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
				</div>
				<div>
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
				</div>
				<div>
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
				</div>
				<div>
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
					<img src="img/card_background.png" alt="Eine nicht aufgedeckte Karte" />
				</div>
			</div>
                    </form>
			<div id="clear-divider"></div>
		</div>
		<div id="footer">
			&copy; 2010 EWA Memory.
		</div>
	</body>
</html>
