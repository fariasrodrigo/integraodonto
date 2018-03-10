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
        <!-- Jquery -->
        <script type="text/javascript" src="<c:url value="https://code.jquery.com/jquery-1.9.1.js" />"></script>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-extension/css/bootstrap-extension.css" />" />
        <!-- Menu CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" />" />
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
                        <li> <a href="painel" class="waves-effect active"><i class="ti-dashboard p-r-10"></i> <span class="hide-menu">Painel</span></a> </li>
                        <li> <a href="javascript:void(0);" class="waves-effect"><i class="ti-calendar p-r-10"></i> <span class="hide-menu"> Agenda <span class="fa arrow"></span></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="#">Doctor Schedule</a></li>
                                <li><a href="#">Book Appointment</a></li>
                            </ul>
                        </li>
                        <li> <a href="javascript:void(0);" class="waves-effect"><i class="icon-people p-r-10"></i> <span class="hide-menu"> Pacientes <span class="fa arrow"></span></span></a>
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
                            <h4 class="page-title"></h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                            <ol class="breadcrumb">
                                <li><a href="painel">${consultorio.nome}</a></li>
                                <li class="active">Painel</li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!--row -->
                    <div class="row">
                        <div class="col-md-3 col-sm-6">
                            <div class="white-box">
                                <div class="r-icon-stats">
                                    <i class="ti-user bg-info"></i>
                                    <div class="bodystate">
                                        <h4>370</h4>
                                        <span class="text-muted">Total de Consultas Hoje</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6">
                            <div class="white-box">
                                <div class="r-icon-stats">
                                    <i class="ti-user bg-megna"></i>
                                    <div class="bodystate">
                                        <h4>342</h4>
                                        <span class="text-muted">Consultas Atendidas</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6">
                            <div class="white-box">
                                <div class="r-icon-stats">
                                    <i class="ti-user bg-danger"></i>
                                    <div class="bodystate">
                                        <h4>13</h4>
                                        <span class="text-muted">Consultas Canceladas</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6">
                            <div class="white-box">
                                <div class="r-icon-stats">
                                    <i class="ti-user bg-inverse"></i>
                                    <div class="bodystate">
                                        <h4>43</h4>
                                        <span class="text-muted">Total de Consultas Semana</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/row -->
                    <!-- /row -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="white-box">
                                <!--  <h3 class="box-title m-b-0">Fluxo de Atendimento</h3> -->
                                <ul class="pager">
                                    <li class="previous"> <a href="#">Agendar</a> </li>
                                    <li class="next"> <a href="#"><span class="text-muted"><i class="fa fa-calendar"></i></span></a> </li>
                                    <li class="next"> <a href="#">Mês</a> </li>
                                    <li class="next"> <a href="#">Semana</a> </li>
                                    <li class="next"> <a href="#">July 3 →</a> </li>
                                    <li class="next"> <a href="#">← July 1</a> </li>
                                </ul>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Status</th>
                                                <th>Horário</th>
                                                <th>Paciente</th>
                                                <th>Telefone</th>
                                                <th>Profissional</th>
                                                <th>Procedimento</th>
                                                <th colspan="2">Interações</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><span class="label label-success">Atendido</span> </td>
                                                <td>11:30</td>
                                                <td>Bruno Martins</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-info">Confirmado</span> </td>
                                                <td>12:30</td>
                                                <td>Ricardo Chastinet</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-info">Confirmado</span> </td>
                                                <td>13:30</td>
                                                <td>Elinaldo Rodrigues</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-warning">A Confirmar</span> </td>
                                                <td>14:30</td>
                                                <td>Leandro Rodrigues</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-info">Limpeza</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-info">Confirmado</span> </td>
                                                <td>15:30</td>
                                                <td>Elizama Farias</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-info">Confirmado</span> </td>
                                                <td>16:30</td>
                                                <td>Marcos Sales</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                            <tr>
                                                <td><span class="label label-warning">A Confirmar</span> </td>
                                                <td>17:30</td>
                                                <td>Rodrigo Farias</td>
                                                <td>(71) 99999-8810</td>
                                                <td>Dr. Ronald McDonald</td>
                                                <td><span class="label label-danger">Extração</span> </td>
                                                <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                <td><span class="text-muted"><i class="fa fa-edit"></i> Reagendar</span></td>
                                            </tr>
                                        </tbody>
                                    </table>
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
        <!-- Sparkline chart JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js" />"></script>
        <!-- jQuery peity -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/peity/jquery.peity.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/peity/jquery.peity.init.js" />"></script>
        <!-- Custom Theme JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/dashboard1.js" />"></script>
        <!--Style Switcher -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/styleswitcher/jQuery.style.switcher.js" />"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/features/features.js" />"></script>
    </body>

</html>
