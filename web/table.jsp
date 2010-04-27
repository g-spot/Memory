<%-- 
    Document   : table
    Created on : Apr 25, 2010, 6:42:58 PM
    Author     : art-jackal
--%>
<jsp:useBean id="memoryBean" class="model.MemoryBean" scope="session"/>
<jsp:useBean id="userBean" class="model.UserBean" scope="session"/>
<jsp:setProperty name="memoryBean" property="*"/>
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
				<li>&nbsp;Userdaten &auml;ndern&nbsp;</li>
				<li>&nbsp;Zur&uuml;ck zur Lounge&nbsp;</li>
				<li>&nbsp;Ausloggen&nbsp;</li>
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
						<td><%= memoryBean.getFoundPairs() %></td>
						<td>n.a.</td></tr>
					<tr>
						<th class="tablehead">Versuche</th>
						<td><%= memoryBean.getTrialCount() %></td>
						<td>n.a.</td>
					</tr>
					<tr>
						<th class="tablehead">Zeit</th>
						<td><%= memoryBean.getCurrentTime() %></td>
						<td>n.a.</td>
					</tr>
				</table>
				<h2>Spielinformationen</h2>
				<table id="gameinfo" summary="Die folgende Tabelle listet die Spielinformationen auf!">
					<tr>
						<th>Restliche Paare</th>
						<td><%= memoryBean.getPairsLeft() %></td>
					</tr>
					<tr>
						<th>Thema</th>
						<td>Alternative Rock</td>
					</tr>
				</table>
				<h2>Top-Künstler</h2>
                                <ul id="favorites">
                                    <li>
                                        <a href="#"><img src="img/muse.jpg" alt="Bild der Alternative Band Muse" />&nbsp;Muse</a>
                                    </li>
                                    <li>
                                        <a href="#"><img src="img/placebo.jpg" alt="Bild der Alternative Band Placebo" />&nbsp;Placebo</a>
                                    </li>
                                    <li>
					<a href="#"><img src="img/radiohead.png" alt="Bild der Alternative Band Radiohead" />&nbsp;Radiohead</a>
                                    </li>
                                    <li>
					<a href="#"><img src="img/incubus.jpeg" alt="Bild der Alternative Band Incubus" />&nbsp;Incubus</a>
                                    </li>
                                    <li>
					<a href="#"><img src="img/foo_fighters.jpeg" alt="Bild der Alternative Band Foo Fighters" />&nbsp;Foo-Fighters</a>
                                    </li>
                                </ul>
			</div>
                    <div id="card-box">
                        <form name="spielbrett" method="POST" action="MemServlet">
                            <table>
                            <tr>
                            <% for(int i=1;i<=16;i++) // TODO insert table size
                               {
                                 if(i > 1 && (i-1)%4 == 0)
                                 {
                            %>
                            </tr>
                            <tr>
                            <%
                                 }
                            %>
                                <td id="card"><input name="<%=i %>" type="image" src="<%= memoryBean.getCards().get(String.valueOf(i)).getFileName() %>" alt="Karte <%=i %>"/></td>
                            <%
                               }
                            %>
                            </tr>
                            </table>
                        </form>
                    </div>
                    <div id="clear-divider"></div>
		</div>
		<div id="footer">
			&copy; 2010 EWA Memory.
		</div>
	</body>
</html>
