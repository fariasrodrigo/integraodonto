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
        <script type="text/javascript" src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" />"></script>
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
        <!-- Date picker plugins css -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.css" />" />

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/custom-select/custom-select.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-select/bootstrap-select.min.css" />" />        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/multiselect/css/multi-select.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bower_components/timepicker/bootstrap-timepicker.min.css" />" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
        <script type="text/javascript" src="<c:url value="/resources/js/features/validation.js" />"></script>
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
                            <h4 class="page-title">PAINEL</h4>
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
                                    <li class="previous"> <a href="#" data-toggle="modal" data-target=".bs-example-modal-lg-adiciona">Agendar</a> </li>
                                    <li class="next"> <a href="#"><span class="text-muted"><i class="fa fa-calendar"></i></span></a> </li>
                                    <li class="next"> <a href="#">Mês</a> </li>
                                    <li class="next"> <a href="#">Semana</a> </li>
                                    <li class="next"> <a href="#">${depois} →</a> </li>
                                    <li class="next"> <a href="#">← ${antes}</a> </li>
                                </ul>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Status</th>
                                                <th>Início</th>
                                                <th>Paciente</th>
                                                <th>Contato</th>
                                                <th>Profissional</th>
                                                <th>Observação</th>
                                                <th colspan="2">Interações</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${consultaList}" var="list">
                                                <tr>
                                                    <td><span class="label label-success">${list.statusConsulta}</span> </td>
                                                    <td>${list.horarioInicioConsulta}</td>
                                                    <td>${list.pacienteTemp}</td>
                                                    <td>${list.celular}</td>
                                                    <td>${list.profissionalTemp}</td>
                                                    <td>${list.obs}</td>
                                                    <td><span class="text-muted"><i class="fa fa-envelope"></i> Enviar SMS</span></td>
                                                    <td><a href="#" data-toggle="modal" data-target=".bs-example-modal-lg-altera" class="text-muted" onclick="setaAlteraAgendamento(${list.id}, '${list.pacienteTemp}', '${list.dataConsulta}', '${list.horarioInicioConsulta}', '${list.horarioFimConsulta}', '${list.profissionalTemp}', '${list.obs}', '${list.statusConsulta}', '${list.lembreteSMS}', '${list.lembreteEMAIL}')"><i class="fa fa-edit"></i> Reagendar</a></td>
                                                </tr>
                                            </c:forEach>
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

        <!-- sample modal adiciona content -->
        <div class="modal fade bs-example-modal-lg-adiciona" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header modal-header-success">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myLargeModalLabel">Agendar Consulta</h4>
                    </div>
                    <form action="agendando-consulta" class="form-horizontal">
                        <div class="modal-body">                        
                            <div class="form-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-8">
                                                <select class="form-control select2" name="pacienteID">
                                                    <option value=ativo">Selecione um paciente</option>
                                                    <c:forEach items="${pacienteList}" var="list">
                                                        <option value="${list.id}">${list.nome}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <button class="btn btn-block btn-outline btn-success">Adicionar Paciente</button>                                              
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input type="text" class="complex-colorpicker form-control" id="datepicker-autoclose" placeholder="Data da consulta" data-mask="99/99/9999" name="dataConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Início da consulta" min="4" max="4" data-mask="99:99" name="horarioInicioConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Fim da consulta" min="4" max="4" data-mask="99:99" name="horarioFimConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <select class="form-control select2" name="profissionalID">
                                                    <option value="">Selecione um profissional</option>
                                                    <c:forEach items="${profissionalList}" var="list">
                                                        <option value="${list.id}">${list.nome}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" placeholder="Observações ex: Avaliação, Orçamento e etc..." name="obs">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <select class="form-control" name="statusConsulta">
                                                        <option value="Status da consulta">Status da consulta</option>
                                                        <option value="Agendada">Agendada</option>
                                                        <option value="Confirmada">Confirmada</option>
                                                        <option value="Finalizada">Finalizada</option>
                                                        <option value="Cancelada">Cancelada</option>
                                                        <option value="Faltou">Faltou</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <select class="form-control" name="lembreteSMS">
                                                            <option value="">Lembrete via SMS</option>
                                                            <option value="sim">Sim</option>
                                                            <option value="nao">Não</option>
                                                        </select>
                                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <select class="form-control" name="lembreteEMAIL">
                                                            <option value="">Lembrete via EMAIL</option>
                                                            <option value="sim">Sim</option>
                                                            <option value="nao">Não</option>
                                                        </select>
                                                        <div class="input-group-addon"><i class="fa fa-send"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/row-->
                            </div>                        
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-md-12">
                                    <div class="modal-footer">                            
                                        <button type="button" class="btn btn-default waves-effect text-left" data-dismiss="modal">Fechar</button>
                                        <button type="submit" class="btn btn-success">Agendar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->

        <!-- sample modal altera content -->
        <div class="modal fade bs-example-modal-lg-altera" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header modal-header-success">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myLargeModalLabel">Reagendar Consulta</h4>
                    </div>
                    <form action="reagendando-consulta" class="form-horizontal">
                        <div class="modal-body">                        
                            <div class="form-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input type="text" name="id" id="alteraId" hidden>
                                                <input type="text" class="form-control" name="" id="alteraPaciente" disabled>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">                                                        
                                                        <input type="text" class="complex-colorpicker form-control" id="alteraData" placeholder="Data da consulta" data-mask="99/99/9999" name="dataConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Início da consulta" min="4" max="4" id="alteraInicio" data-mask="99:99" name="horarioInicioConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Fim da consulta" min="4" max="4" id="alteraFim" data-mask="99:99" name="horarioFimConsulta">
                                                        <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" name="" id="alteraProfissional" disabled>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" placeholder="Observações ex: Avaliação, Orçamento e etc..." name="obs" id="alteraObs">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <select class="form-control" name="statusConsulta" id="alteraStats">
                                                        <option value="Status da consulta">Status da consulta</option>
                                                        <option value="Agendada">Agendada</option>
                                                        <option value="Confirmada">Confirmada</option>
                                                        <option value="Finalizada">Finalizada</option>
                                                        <option value="Cancelada">Cancelada</option>
                                                        <option value="Faltou">Faltou</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <select class="form-control" name="lembreteSMS" id="alteraSms">
                                                            <option value="">Lembrete via SMS</option>
                                                            <option value="sim">Sim</option>
                                                            <option value="nao">Não</option>
                                                        </select>
                                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <select class="form-control" name="lembreteEMAIL" id="alteraEmail">
                                                            <option value="">Lembrete via EMAIL</option>
                                                            <option value="sim">Sim</option>
                                                            <option value="nao">Não</option>
                                                        </select>
                                                        <div class="input-group-addon"><i class="fa fa-send"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/row-->
                            </div>                        
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-md-12">
                                    <div class="modal-footer">                            
                                        <button type="button" class="btn btn-default waves-effect text-left" data-dismiss="modal">Fechar</button>
                                        <button type="submit" class="btn btn-warning">Reagendar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
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
        <!-- Sparkline chart JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js" />"></script>
        <!-- jQuery peity -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/peity/jquery.peity.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/peity/jquery.peity.init.js" />"></script>
        <!-- Custom Theme JavaScript -->
        <script type="text/javascript" src="<c:url value="/resources/js/custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mask.js" />"></script>

        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/custom-select/custom-select.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-select/bootstrap-select.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/switchery/dist/switchery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/multiselect/js/jquery.multi-select.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/timepicker/bootstrap-timepicker.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.js" />"></script>

        <script>
                                                        // Switchery
                                                        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
                                                        $('.js-switch').each(function () {
                                                            new Switchery($(this)[0], $(this).data());

                                                        });

                                                        // For select 2
                                                        $(".select2").select2();
                                                        $('.selectpicker').selectpicker();

                                                        jQuery('.mydatepicker, #datepicker').datepicker();
                                                        jQuery('#datepicker-autoclose').datepicker({
                                                            autoclose: true,
                                                            todayHighlight: true
                                                        });

                                                        // Date Picker
                                                        jQuery('.mydatepicker, #datepicker').datepicker();
                                                        jQuery('#datepicker-autoclose').datepicker({
                                                            autoclose: true,
                                                            todayHighlight: true
                                                        });

                                                        jQuery('#date-range').datepicker({
                                                            toggleActive: true
                                                        });
                                                        jQuery('#datepicker-inline').datepicker({

                                                            todayHighlight: true
                                                        });
        </script>
        <!--Style Switcher -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/bower_components/styleswitcher/jQuery.style.switcher.js" />"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/features/features.js" />"></script>
    </body>

</html>
