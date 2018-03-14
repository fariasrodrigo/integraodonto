<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" type="text/css" href="<c:url value="/resources/plugins/images/favicon.png" />" />
        <title>IntegraOdonto - Hospital admin dashboard web app kit</title>
        <!-- Jquery -->
        <script type="text/javascript" src="<c:url value="https://code.jquery.com/jquery-1.9.1.js" />"></script>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-extension/css/bootstrap-extension.css" />" />
        <!-- animation CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />" />
        <!-- Wizard CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/register-steps/steps.css" />" />
        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
        <!-- color CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/colors/megna.css" />" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-19175540-9', 'auto');
            ga('send', 'pageview');

        </script>
    </head>
    <body>
        <!-- Preloader -->
        <div class="preloader">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <section id="wrapper" class="step-register">
            <div class="register-box">
                <div class="">
                    <a href="javascript:void(0)" class="text-center db m-b-40"><img src="<c:url value="/resources/plugins/images/eliteadmin-logo-dark.png" />" alt="Home" /><br/><img src="<c:url value="/resources/plugins/images/eliteadmin-text-dark.png" />" alt="Home" /></a>
                    <!-- multistep form -->
                    <form id="msform" action="adiciona-consultorio" method="post" role="form">
                        <!-- progressbar -->
                        <ul id="eliteregister">
                            <li class="active">Consultório</li>
                            <li>Pessoal</li>
                            <li>Acesso</li>
                        </ul>
                        <!-- fieldsets -->
                        <fieldset>
                            <h2 class="fs-title">Configurações do Consultório</h2>
                            <h3 class="fs-subtitle">Qual o nome do Consultório?</h3>
                            <input type="text" name="nomeConsultorio" required="" placeholder="Nome do Consultório" />
                            <input type="button" name="next" class="next action-button" value="Próximo" />
                        </fieldset>
                        <fieldset>
                            <h2 class="fs-title">Configurações Pessoais</h2>
                            <h3 class="fs-subtitle">Qual o seu nome?</h3>
                            <input type="text" name="nomeProfissional" required="" placeholder="Nome" />
                            <input type="button" name="previous" class="previous action-button" value="Anterior" />
                            <input type="button" name="next" class="next action-button" value="Próximo" />
                        </fieldset>
                        <fieldset>
                            <h2 class="fs-title">Configurações de Acesso</h2>
                            <h3 class="fs-subtitle">Crie agora os dados de acesso ao IntegraOdonto</h3>
                            <input type="email" name="login" required="" placeholder="Crie o seu Login" />
                            <input type="password" name="senha1" required="" placeholder="Crie a sua Senha" />
                            <input type="password" name="senha2" required="" placeholder="Confirme a sua Senha" />
                            <input type="button" name="previous" class="previous action-button" value="Anterior" />
                            <input type="submit" name="next" class="next action-button" value="Próximo" />
                        </fieldset>
                    </form>
                    <div class="clear"></div>
                </div>
            </div>
        </section>
        <!-- jQuery -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/jquery/dist/jquery.min.js" />"></script>
        <!-- Bootstrap Core JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/tether.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-extension/js/bootstrap-extension.min.js" />"></script>
        <!-- Menu Plugin JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/register-steps/jquery.easing.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/register-steps/register-init.js" />"></script>
        <!--slimscroll JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.slimscroll.js" />"></script>
        <!--Wave Effects -->
        <script type="text/javascript" src="<c:url value="/resources/js/waves.js" />"></script>
        <!-- Custom Theme JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/custom.min.js" />"></script>
        <!--Style Switcher -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/styleswitcher/jQuery.style.switcher.js" />"></script>
    </body>
</html>
