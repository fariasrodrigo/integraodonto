<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- App Favicon -->
        <link rel="shortcut icon" type="text/css" href="<c:url value="/resources/plugins/images/favicon.png" />" />
        <title>IntegraOdonto - Hospital admin dashboard web app kit</title>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-extension/css/bootstrap-extension.css" />" />
        <!-- Menu CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.css" />" />
        <!-- animation CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />" />
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
        <script type="text/javascript" src="<c:url value="/resources/js/features/validation.js" />"></script>
        <!-- Jquery -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o), m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
            ga('create', 'UA-19175540-9', 'auto');
            ga('send', 'pageview');

            (function ($) {
                remove = function (item) {
                    var tr = $(item).closest('tr');
                    tr.fadeOut(400, function () {
                        tr.remove();
                    });
                    return false;
                }
            })(jQuery);

            function depoisDeExcluir(dadosDoController) {
                window.history.pushState({}, document.title, "todos-pacientes");
                alert("Exluído com sucesso!");
            }
            function excluir(id) {
                $.get("deletando-paciente?id=" + id, depoisDeExcluir);
            }

            // api viacep
            $(document).ready(function () {

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $("#endereco").val("");
                    $("#bairro").val("");
                    $("#cidade").val("");
                    $("#estado").val("");
                    $("#ibge").val("");
                }

                //Quando o campo cep perde o foco.
                $("#cep").blur(function () {

                    //Nova variável "cep" somente com dígitos.
                    var cep = $(this).val().replace(/\D/g, '');

                    //Verifica se campo cep possui valor informado.
                    if (cep != "") {

                        //Expressão regular para validar o CEP.
                        var validacep = /^[0-9]{8}$/;

                        //Valida o formato do CEP.
                        if (validacep.test(cep)) {

                            //Preenche os campos com "..." enquanto consulta webservice.
                            $("#endereco").val("...");
                            $("#bairro").val("...");
                            $("#cidade").val("...");
                            $("#estado").val("...");

                            //Consulta o webservice viacep.com.br/
                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                                if (!("erro" in dados)) {
                                    //Atualiza os campos com os valores da consulta.
                                    $("#endereco").val(dados.logradouro);
                                    $("#bairro").val(dados.bairro);
                                    $("#cidade").val(dados.localidade);
                                    $("#estado").val(dados.uf);
                                } //end if.
                                else {
                                    //CEP pesquisado não foi encontrado.
                                    limpa_formulário_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } //end if.
                        else {
                            //cep é inválido.
                            limpa_formulário_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } //end if.
                    else {
                        //cep sem valor, limpa formulário.
                        limpa_formulário_cep();
                    }
                });
            });
        </script>
    </head>

    <body>
        <!-- Preloader -->
        <div class="preloader">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top m-b-0">
                <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
                    <div class="top-left-part"><a class="logo" href="index.html"><b><img src="<c:url value="/resources/plugins/images/eliteadmin-logo.png" />" alt="home" /></b><span class="hidden-xs"><strong>integra</strong>odonto</span></a></div>
                    <ul class="nav navbar-top-links navbar-left hidden-xs">
                        <li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
                        <li>
                            <form role="search" class="app-search hidden-xs">
                                <input type="text" placeholder="Busca..." class="form-control"> <a href=""><i class="fa fa-search"></i></a> </form>
                        </li>
                    </ul>
                    <ul class="nav navbar-top-links navbar-right pull-right">
                        <li class="dropdown"> <a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"><i class="icon-envelope"></i>
                                <div class="notify"><span class="heartbit"></span><span class="point"></span></div>
                            </a>
                            <!-- /.dropdown-messages -->
                        </li>
                        <!-- /.dropdown -->
                        <li class="dropdown"> <a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"><i class="icon-note"></i>
                                <div class="notify"><span class="heartbit"></span><span class="point"></span></div>
                            </a>
                            <!-- /.dropdown-tasks -->
                        </li>
                        <!-- /.dropdown -->
                        <li class="dropdown">
                            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="<c:url value="/resources/plugins/images/users/d1.jpg" />" alt="user-img" width="36" class="img-circle"><b class="hidden-xs">${profissional.nome}</b> </a>
                            <ul class="dropdown-menu dropdown-user animated flipInY">
                                <li><a href="meu-perfil"><i class="ti-user"></i>  Meu Perfil</a></li>
                                <li><a href="javascript:void(0)"><i class="ti-email"></i>  Inbox</a></li>
                                <li><a href="javascript:void(0)"><i class="ti-settings"></i>  Configurações</a></li>
                                <li><a href="logout"><i class="fa fa-power-off"></i>  Sair</a></li>
                            </ul>
                            <!-- /.dropdown-user -->
                        </li>
                        <li class="right-side-toggle"> <a class="waves-effect waves-light" href="javascript:void(0)"><i class="ti-settings"></i></a></li>
                        <!-- /.dropdown -->
                    </ul>
                </div>
                <!-- /.navbar-header -->
                <!-- /.navbar-top-links -->
                <!-- /.navbar-static-side -->
            </nav>
            <!-- Left navbar-header -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search hidden-sm hidden-md hidden-lg">
                            <!-- input-group -->
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Busca..."> <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"> <i class="fa fa-search"></i> </button>
                                </span> </div>
                            <!-- /input-group -->
                        </li>
                        <li class="user-pro">
                            <a href="#" class="waves-effect"><img src="<c:url value="/resources/plugins/images/users/d1.jpg" />" alt="user-img" class="img-circle"> <span class="hide-menu">${profissional.nome}<span class="fa arrow"></span></span>
                            </a>
                            <ul class="nav nav-second-level">
                                <li><a href="meu-perfil"><i class="ti-user"></i> Meu Perfil</a></li>
                                <li><a href="javascript:void(0)"><i class="ti-email"></i> Inbox</a></li>
                                <li><a href="javascript:void(0)"><i class="ti-settings"></i> Configurações</a></li>
                                <li><a href="javascript:void(0)"><i class="fa fa-power-off"></i> Sair</a></li>
                            </ul>
                        </li>
                        <li class="nav-small-cap m-t-10">--- Menu</li>
                        <li> <a href="painel" class="waves-effect"><i class="ti-dashboard p-r-10"></i> <span class="hide-menu">Painel</span></a> </li>
                        <li> <a href="javascript:void(0);" class="waves-effect"><i class="ti-calendar p-r-10"></i> <span class="hide-menu"> Agenda <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="#">Doctor Schedule</a></li>
                                <li><a href="#">Book Appointment</a></li>
                            </ul>
                        </li>
                        <li> <a href="javascript:void(0);" class="waves-effect active"><i class="icon-people p-r-10"></i> <span class="hide-menu"> Pacientes <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="todos-pacientes">Todos os Pacientes</a> </li>
                                <li> <a href="adiciona-paciente">Adicionar Pacientes</a> </li>
                            </ul>
                        </li>

                        <li> <a href="javascript:void(0);" class="waves-effect" ${hidden}><i class="fa fa-send-o p-r-10"></i> <span class="hide-menu"> Marketing <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="#">SMS Marketing</a></li>
                                <li><a href="#">Email Marketing</a></li>
                            </ul>
                        </li>

                        <li> <a href="javascript:void(0);" class="waves-effect" ${hidden}><i class="icon-chart p-r-10"></i> <span class="hide-menu"> Relatórios <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="#">Consultas</a></li>
                                <li><a href="#">Pacientes</a></li>
                            </ul>
                        </li>

                        <li> <a href="javascript:void(0);" class="waves-effect" ${hidden}><i class="fa fa-inr p-r-10"></i> <span class="hide-menu"> Financeiro <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="#">Contas a Pagar</a></li>
                                <li><a href="#">Contas a Receber</a></li>
                                <li> <a href="#">Fluxo de Caixa</a></li>
                                <li> <a href="#">Orçamentos</a></li>
                            </ul>
                        </li>

                        <li> <a href="javascript:void(0);" class="waves-effect"><i data-icon="P" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu"> Configurações <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="meu-perfil">Meu Perfil</a></li>
                                <li> <a href="consultorio" ${hidden}>Meu Consultório</a></li>
                                <li> <a href="adiciona-profissional" ${hidden}>Profissionais</a></li>
                                <li> <a href="#" ${hidden}>Aviso por SMS</a></li>
                                <li> <a href="procedimentos" ${hidden}>Procedimentos</a></li>
                                <li> <a href="#" ${hidden}>Modelo de Receita</a></li>
                                <li> <a href="#" ${hidden}>Planos de Saúde</a></li>
                            </ul>
                        </li>
                        <li><a href="logout" class="waves-effect"><i class="icon-logout fa-fw"></i> <span class="hide-menu">Sair</span></a></li>
                    </ul>
                </div>
            </div>
            <!-- Left navbar-header end -->
            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row bg-title">
                        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                            <h4 class="page-title">PACIENTES</h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                            <ol class="breadcrumb">
                                <li><a href="painel">${consultorio.nome}</a></li>
                                <li class="active">Todos os Pacientes</li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- .row -->
                    <div class="row">
                        <div class="col-lg-12 col-sm-12 col-xs-12">
                            <div class="white-box">
                                <h3 class="box-title"></h3>
                                <p class="text-muted m-b-30">Visualize e adicione pacientes aqui</p>
                                <!-- Nav tabs -->
                                <ul class="nav customtab nav-tabs" role="tablist">
                                    <li role="presentation" class="nav-item"><a href="todos-pacientes" class="nav-link active" ><span class="visible-xs"><i class="ti-home"></i></span><span class="hidden-xs"> Todos os Pacientes</span></a></li>
                                    <li role="presentation" class="nav-item"><a href="adiciona-paciente"><span class="visible-xs"><i class="ti-user"></i></span> <span class="hidden-xs">Adicionar Paciente</span></a></li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade active in">
                                        <div class="col-lg-12">
                                            <div class="white-box">
                                                <div class="table-responsive">
                                                    <table class="table table-hover table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>Prontuário</th>
                                                                <th>Nome</th>
                                                                <th>Última Consulta</th>
                                                                <th>Próxima Consulta</th>
                                                                <th colspan="6">Interações</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${pacienteList}" var="list">
                                                                <tr>
                                                                    <td>${list.prontuario}</td>
                                                                    <td>${list.nome}</td>
                                                                    <td><a class="text-muted"><i class="fa fa-clock-o"></i> Oct 16, 2017</a> </td>
                                                                    <td><a><i class="fa fa-clock-o"></i> May 11, 2018</a> </td>
                                                                    <td><a href='#'><i class="fa fa-send"></i> Enviar Email</a></td>
                                                                    <td><a href='#'><i class="fa fa-envelope"></i> Enviar SMS</a></td>                                                                    
                                                                    <td><a href='#' onclick="setaPerfilTodosPacientes('${list.nome}', '${list.prontuario}', '${list.nascimento}', '${list.cpf}', '${list.rg}', '${list.celular}', '${list.fixo}', '${list.email}', '${list.cep}', '${list.endereco}', '${list.numero}', '${list.compl}', '${list.bairro}', '${list.cidade}', '${list.estado}')" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa  fa-info-circle"></i> Informações</a></td>
                                                                    <td><a href='#'><i class="fa fa-user "></i> Visualizar Perfil</a></td>
                                                                    <td><a href="editar-paciente?id=${list.id}"><i class="fa fa-edit"></i> Editar</a></td>
                                                                    <td><a href='#' onclick="remove(this);excluir(${list.id})"><i class="fa fa-trash-o"></i> Excluir</a></td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->
                    <!-- .right-sidebar -->
                    <div class="right-sidebar">
                        <div class="slimscrollright">
                            <div class="rpanel-title"> Service Panel <span><i class="ti-close right-side-toggle"></i></span> </div>
                            <div class="r-panel-body">
                                <ul>
                                    <li><b>Layout Options</b></li>
                                    <li>
                                        <div class="checkbox checkbox-info">
                                            <input id="checkbox1" type="checkbox" class="fxhdr">
                                            <label for="checkbox1"> Fix Header </label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="checkbox checkbox-warning">
                                            <input id="checkbox2" type="checkbox" class="fxsdr">
                                            <label for="checkbox2"> Fix Sidebar </label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="checkbox checkbox-success">
                                            <input id="checkbox4" type="checkbox" class="open-close">
                                            <label for="checkbox4"> Toggle Sidebar </label>
                                        </div>
                                    </li>
                                </ul>
                                <ul id="themecolors" class="m-t-20">
                                    <li><b>With Light sidebar</b></li>
                                    <li><a href="javascript:void(0)" theme="default" class="default-theme">1</a></li>
                                    <li><a href="javascript:void(0)" theme="green" class="green-theme">2</a></li>
                                    <li><a href="javascript:void(0)" theme="gray" class="yellow-theme">3</a></li>
                                    <li><a href="javascript:void(0)" theme="blue" class="blue-theme">4</a></li>
                                    <li><a href="javascript:void(0)" theme="purple" class="purple-theme">5</a></li>
                                    <li><a href="javascript:void(0)" theme="megna" class="megna-theme working">6</a></li>
                                    <li><b>With Dark sidebar</b></li>
                                    <br/>
                                    <li><a href="javascript:void(0)" theme="default-dark" class="default-dark-theme">7</a></li>
                                    <li><a href="javascript:void(0)" theme="green-dark" class="green-dark-theme">8</a></li>
                                    <li><a href="javascript:void(0)" theme="gray-dark" class="yellow-dark-theme">9</a></li>
                                    <li><a href="javascript:void(0)" theme="blue-dark" class="blue-dark-theme">10</a></li>
                                    <li><a href="javascript:void(0)" theme="purple-dark" class="purple-dark-theme">11</a></li>
                                    <li><a href="javascript:void(0)" theme="megna-dark" class="megna-dark-theme">12</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /.right-sidebar -->
                </div>
                <!-- /.container-fluid -->
                <footer class="footer text-center"> 2018 &copy; IntegraOdonto </footer>
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- sample modal content -->
        <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myLargeModalLabel">Large modal</h4>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-12 col-xs-12">
                            <div class="white-box">
                                <div class="row">
                                    <div class="col-md-3 col-xs-6 b-r"> <strong>Prontuário</strong>
                                        <br>
                                        <p class="text-muted" id="pacienteprontuario"></p>
                                    </div>
                                    <div class="col-md-3 col-xs-6 b-r"> <strong>CPF</strong>
                                        <br>
                                        <p class="text-muted" id="pacientecpf"></p>
                                    </div>
                                    <div class="col-md-3 col-xs-6 b-r"> <strong>RG</strong>
                                        <br>
                                        <p class="text-muted" id="pacienterg"></p>
                                    </div>
                                    <div class="col-md-3 col-xs-6"> <strong>Nascimento</strong>
                                        <br>
                                        <p class="text-muted" id="pacientenascimento"></p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-md-4 col-xs-6 b-r"> <strong>Celular</strong>
                                        <br>
                                        <p class="text-muted" id="pacientecelular"></p>
                                    </div>
                                    <div class="col-md-4 col-xs-6 b-r"> <strong>Fixo</strong>
                                        <br>
                                        <p class="text-muted" id="pacientefixo"></p>
                                    </div>
                                    <div class="col-md-4 col-xs-6"> <strong>Email</strong>
                                        <br>
                                        <p class="text-muted" id="pacienteemail"></p>
                                    </div>
                                </div>
                                <hr>
                                <br>
                                <h4 class="m-t-30">Endereço</h4>
                                <p class="m-t-30" id="pacienteendereco"></p>
                                <p class="m-t-30" id="pacientecep"></p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect text-left" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->

        <!-- jQuery -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/jquery/dist/jquery.min.js" />"></script>
        <!-- Bootstrap Core JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/tether.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-extension/js/bootstrap-extension.min.js" />"></script>
        <!-- Menu Plugin JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js" />"></script>
        <!--slimscroll JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.slimscroll.js" />"></script>
        <!--Wave Effects -->
        <script type="text/javascript" src="<c:url value="/resources/js/waves.js" />"></script>
        <!-- Date Picker Plugin JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.js" />"></script>
        <script type="text/javascript">
                                                                        // Date Picker
                                                                        jQuery('.mydatepicker').datepicker();
        </script>
        <!-- Custom Theme JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jasny-bootstrap.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mask.js" />"></script>
        <!--Style Switcher -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/styleswitcher/jQuery.style.switcher.js" />"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/features/features.js" />"></script>
    </body>

</html>
