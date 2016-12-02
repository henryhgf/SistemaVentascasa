-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-03-2014 a las 17:19:39
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbventas`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Categoria` (`pdescripcion` VARCHAR(100))  BEGIN		
		INSERT INTO categoria(descripcion)
		VALUES(pdescripcion);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Cliente` (`pnombre` VARCHAR(100), `pruc` VARCHAR(11), `pdni` VARCHAR(8), `pdireccion` VARCHAR(50), `ptelefono` VARCHAR(15), `pobsv` TEXT)  BEGIN		
		INSERT INTO cliente(nombre,ruc,dni,direccion,telefono,obsv)
		VALUES(pnombre,pruc,pdni,pdireccion,ptelefono,pobsv);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Compra` (`pidtipodocumento` INT, `pidproveedor` INT, `pidempleado` INT, `pnumero` VARCHAR(20), `pfecha` DATE, `psubtotal` DECIMAL(8,2), `pigv` DECIMAL(8,2), `ptotal` DECIMAL(8,2), `pestado` VARCHAR(30))  BEGIN		
		INSERT INTO compra(idtipodocumento,idproveedor,idempleado,numero,fecha,subtotal,igv,total,estado)
		VALUES(pidtipodocumento,pidproveedor,pidempleado,pnumero,pfecha,psubtotal,pigv,ptotal,pestado);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_DetalleCompra` (`pidcompra` INT, `pidproducto` INT, `pcantidad` DECIMAL(8,2), `pprecio` DECIMAL(8,2), `ptotal` DECIMAL(8,2))  BEGIN		
		INSERT INTO detallecompra(idcompra,idproducto,cantidad,precio,total)
		VALUES(pidcompra,pidproducto,pcantidad,pprecio,ptotal);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_DetalleVenta` (`pidventa` INT, `pidproducto` INT, `pcantidad` DECIMAL(8,2), `pcosto` DECIMAL(8,2), `pprecio` DECIMAL(8,2), `ptotal` DECIMAL(8,2))  BEGIN		
		INSERT INTO detalleventa(idventa,idproducto,cantidad,costo,precio,total)
		VALUES(pidventa,pidproducto,pcantidad,pcosto,pprecio,ptotal);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Empleado` (`pnombre` VARCHAR(50), `papellido` VARCHAR(80), `psexo` VARCHAR(1), `pfechanac` DATE, `pdireccion` VARCHAR(100), `ptelefono` VARCHAR(10), `pcelular` VARCHAR(15), `pemail` VARCHAR(80), `pdni` VARCHAR(8), `pfechaing` DATE, `psueldo` DECIMAL(8,2), `pestado` VARCHAR(30), `pusuario` VARCHAR(20), `pcontraseña` TEXT, `pidtipousuario` INT)  BEGIN		
		INSERT INTO empleado(nombre,apellido,sexo,fechanac,direccion,telefono,celular,email,dni,fechaing,sueldo,estado,usuario,contraseña,idtipousuario)
		VALUES(pnombre,papellido,psexo,pfechanac,pdireccion,ptelefono,pcelular,pemail,pdni,pfechaing,psueldo,pestado,pusuario,pcontraseña,pidtipousuario);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Producto` (`pcodigo` VARCHAR(50), `pnombre` VARCHAR(100), `pdescripcion` TEXT, `pstock` DECIMAL(8,2), `pstockmin` DECIMAL(8,2), `ppreciocosto` DECIMAL(8,2), `pprecioventa` DECIMAL(8,2), `putilidad` DECIMAL(8,2), `pestado` VARCHAR(30), `pidcategoria` INT)  BEGIN		
		INSERT INTO producto(codigo,nombre,descripcion,stock,stockmin,preciocosto,precioventa,utilidad,estado,idcategoria)
		VALUES(pcodigo,pnombre,pdescripcion,pstock,pstockmin,ppreciocosto,pprecioventa,putilidad,pestado,pidcategoria);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Proveedor` (`pnombre` VARCHAR(100), `pruc` VARCHAR(11), `pdni` VARCHAR(8), `pdireccion` VARCHAR(100), `ptelefono` VARCHAR(10), `pcelular` VARCHAR(15), `pemail` VARCHAR(80), `pcuenta1` VARCHAR(50), `pcuenta2` VARCHAR(50), `pestado` VARCHAR(30), `pobsv` TEXT)  BEGIN		
		INSERT INTO proveedor(nombre,ruc,dni,direccion,telefono,celular,email,cuenta1,cuenta2,estado,obsv)
		VALUES(pnombre,pruc,pdni,pdireccion,ptelefono,pcelular,pemail,pcuenta1,pcuenta2,pestado,pobsv);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_TipoDocumento` (`pdescripcion` VARCHAR(80))  BEGIN		
		INSERT INTO tipodocumento(descripcion)
		VALUES(pdescripcion);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_TipoUsuario` (`pdescripcion` VARCHAR(80), `pp_venta` INT, `pp_compra` INT, `pp_producto` INT, `pp_proveedor` INT, `pp_empleado` INT, `pp_cliente` INT, `pp_categoria` INT, `pp_tipodoc` INT, `pp_tipouser` INT, `pp_anularv` INT, `pp_anularc` INT, `pp_estadoprod` INT, `pp_ventare` INT, `pp_ventade` INT, `pp_estadistica` INT, `pp_comprare` INT, `pp_comprade` INT, `pp_pass` INT, `pp_respaldar` INT, `pp_restaurar` INT, `pp_caja` INT)  BEGIN		
		INSERT INTO tipousuario(descripcion,p_venta,p_compra,p_producto,p_proveedor,p_empleado,p_cliente,p_categoria,p_tipodoc,p_tipouser,p_anularv,p_anularc,
		p_estadoprod,p_ventare,p_ventade,p_estadistica,p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja)
		VALUES(pdescripcion,pp_venta,pp_compra,pp_producto,pp_proveedor,pp_empleado,pp_cliente,pp_categoria,pp_tipodoc,pp_tipouser,pp_anularv,pp_anularc,
		pp_estadoprod,pp_ventare,pp_ventade,pp_estadistica,pp_comprare,pp_comprade,pp_pass,pp_respaldar,pp_restaurar,pp_caja);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Venta` (`pidtipodocumento` INT, `pidcliente` INT, `pidempleado` INT, `pserie` VARCHAR(5), `pnumero` VARCHAR(20), `pfecha` DATE, `ptotalventa` DECIMAL(8,2), `pdescuento` DECIMAL(8,2), `psubtotal` DECIMAL(8,2), `pigv` DECIMAL(8,2), `ptotalpagar` DECIMAL(8,2), `pestado` VARCHAR(30))  BEGIN		
		INSERT INTO venta(idtipodocumento,idcliente,idempleado,serie,numero,fecha,totalventa,descuento,subtotal,igv,totalpagar,estado)
		VALUES(pidtipodocumento,pidcliente,pidempleado,pserie,pnumero,pfecha,ptotalventa,pdescuento,psubtotal,pigv,ptotalpagar,pestado);
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Categoria` ()  BEGIN
		SELECT * FROM categoria;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CategoriaPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.IdCategoria=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Cliente` ()  BEGIN
		SELECT * FROM cliente;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ClientePorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.IdCliente=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "ruc" THEN
		SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Ruc LIKE CONCAT("%",pbusqueda,"%");
   ELSEIF pcriterio = "dni" THEN
		SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Dni LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Compra` ()  BEGIN
		SELECT c.IdCompra,td.Descripcion AS TipoDocumento,p.Nombre AS Proveedor,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,c.Numero,c.Fecha,c.SubTotal,c.Igv,c.Total,c.Estado
		FROM compra AS c
		INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento	 
		INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor		
		INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
		ORDER BY c.IdCompra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CompraPorDetalle` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE)  BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,ca.Descripcion AS Categoria,dc.Precio,
			SUM(dc.Cantidad) AS Cantidad,SUM(dc.Total) AS Total FROM compra AS c
			INNER JOIN detallecompra AS dc ON c.IdCompra=dc.IdCompra
			INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
			INNER JOIN categoria AS ca ON p.IdCategoria=ca.IdCategoria
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND c.Estado="NORMAL" GROUP BY p.IdProducto
			ORDER BY c.IdCompra DESC;
		END IF;

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CompraPorFecha` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE, `pdocumento` VARCHAR(30))  BEGIN
		IF pcriterio = "anular" THEN
			SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY c.IdCompra DESC;
		ELSEIF pcriterio = "consultar" THEN
		   SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE c.Fecha>=pfechaini AND c.Fecha<=pfechafin ORDER BY c.IdCompra DESC;
		END IF;

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleCompra` ()  BEGIN
		SELECT * FROM detallecompra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleCompraPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT dc.IdCompra,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dc.Cantidad,dc.Precio,dc.Total  FROM detallecompra AS dc
				INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
				WHERE dc.IdCompra=pbusqueda ORDER BY dc.IdCompra;
			
			END IF; 
			
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleVenta` ()  BEGIN
		SELECT * FROM detalleventa;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleVentaPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT dv.IdVenta,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dv.Cantidad,dv.Precio,dv.Total  FROM detalleventa AS dv
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE dv.IdVenta=pbusqueda ORDER BY dv.IdVenta;
			
			END IF; 
			
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Empleado` ()  BEGIN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario=tu.IdTipoUsuario
		ORDER BY e.IdEmpleado;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_EmpleadoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE e.IdEmpleado=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE ((e.Nombre LIKE CONCAT("%",pbusqueda,"%")) OR (e.Apellido LIKE CONCAT("%",pbusqueda,"%")));
	ELSEIF pcriterio = "dni" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE e.Dni LIKE CONCAT("%",pbusqueda,"%");
	ELSE
	   SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Login` (`pusuario` VARCHAR(20), `pcontraseña` TEXT, `pdescripcion` VARCHAR(80))  BEGIN
	
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE e.Usuario = pusuario AND e.`Contraseña` = pcontraseña AND tu.Descripcion=pdescripcion;
		
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_LoginPermisos` (`pnombre_usuario` VARCHAR(20), `pdescripcion_tipousuario` VARCHAR(80))  BEGIN
	
		SELECT tu.IdTipoUsuario,e.Usuario,tu.Descripcion,tu.p_venta,tu.p_compra,tu.p_producto,tu.p_proveedor,tu.p_empleado,tu.p_cliente,tu.p_categoria,tu.p_tipodoc,tu.p_tipouser,
		tu.p_anularv,tu.p_anularc,tu.p_estadoprod,tu.p_ventare,tu.p_ventade,tu.p_estadistica,tu.p_comprare,tu.p_comprade,tu.p_pass,tu.p_respaldar,tu.p_restaurar,tu.p_caja
		FROM tipousuario AS tu INNER JOIN empleado AS e ON tu.IdTipoUsuario = e.IdTipoUsuario WHERE e.Usuario = pnombre_usuario AND tu.Descripcion=pdescripcion_tipousuario;
		
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Producto` ()  BEGIN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
		ORDER BY p.IdProducto;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoActivo` ()  BEGIN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria WHERE p.Estado="Activo"
		ORDER BY p.IdProducto;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoActivoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(50))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.IdProducto=pbusqueda AND p.Estado="Activo";
 	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda AND p.Estado="Activo";
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "categoria" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Estado="Activo";
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(50))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.IdProducto=pbusqueda;
	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "categoria" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoVerificarCodigoBar` (`pbusqueda` VARCHAR(50))  BEGIN
	
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda;

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Proveedor` ()  BEGIN
		SELECT * FROM proveedor;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProveedorPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.IdProveedor=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "ruc" THEN
		SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Ruc LIKE CONCAT("%",pbusqueda,"%");
   ELSEIF pcriterio = "dni" THEN
		SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Dni LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoDocumento` ()  BEGIN
		SELECT * FROM tipodocumento;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoDocumentoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.IdTipoDocumento=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoUsuario` ()  BEGIN
		SELECT * FROM tipousuario;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoUsuarioPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.IdTipoUsuario=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT * FROM tipousuario AS tp;
	END IF; 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_UltimoIdCompra` ()  BEGIN
		SELECT MAX(IdCompra) AS id FROM compra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_UltimoIdVenta` ()  BEGIN
		SELECT MAX(IdVenta) AS id FROM venta;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Venta` ()  BEGIN
		SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
		v.Igv,v.TotalPagar,v.Estado
		FROM venta AS v 
		INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
		INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
		INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
		ORDER BY v.IdVenta;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaMensual` (`pcriterio` VARCHAR(20), `pfecha_ini` VARCHAR(20), `pfecha_fin` VARCHAR(20))  BEGIN
			IF pcriterio = "consultar" THEN
				SELECT CONCAT(UPPER(MONTHNAME(v.Fecha))," ",YEAR(v.Fecha)) AS Fecha,SUM(v.TotalVenta) AS Total,
				ROUND((SUM(v.TotalVenta)*100)/(SELECT SUM(v.TotalPagar) AS TotalVenta FROM venta AS v WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO")) AS Porcentaje
				FROM venta AS v
				WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO" GROUP BY v.Fecha;			
								
			END IF; 
			

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorDetalle` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE)  BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,c.Descripcion AS Categoria,dv.Costo,dv.Precio,
			SUM(dv.Cantidad) AS Cantidad,SUM(dv.Total) AS Total,
			SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
		END IF;

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorFecha` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE, `pdocumento` VARCHAR(30))  BEGIN
		IF pcriterio = "anular" THEN
			SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalPagar  FROM venta AS v
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY v.IdVenta DESC;
		ELSEIF pcriterio = "consultar" THEN
		   SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalVenta,v.Descuento,v.TotalPagar  FROM venta AS v
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) ORDER BY v.IdVenta DESC;
		ELSEIF pcriterio = "caja" THEN	
		   SELECT SUM(dv.Cantidad) AS Cantidad,p.Nombre AS Producto,dv.Precio,
			SUM(dv.Total) AS Total, SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia,v.Fecha FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE v.Fecha=pfechaini AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
		END IF;

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Igv,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			ELSEIF pcriterio = "documento" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Igv,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE td.Descripcion=pbusqueda ORDER BY v.IdVenta;
			END IF; 
			

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Venta_DetallePorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Igv,v.TotalPagar,v.Estado,p.Codigo,p.Nombre,dv.Cantidad,p.PrecioVenta,dv.Total  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			
			END IF; 
			

	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarCompraEstado` (`pidcompra` INT, `pestado` VARCHAR(30))  BEGIN
		UPDATE compra SET
			estado=pestado
		WHERE idcompra = pidcompra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarProductoStock` (`pidproducto` INT, `pstock` DECIMAL(8,2))  BEGIN
		UPDATE producto SET
			stock=pstock
		WHERE idproducto = pidproducto;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarVentaEstado` (`pidventa` INT, `pestado` VARCHAR(30))  BEGIN
		UPDATE venta SET
			estado=pestado
		WHERE idventa = pidventa;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_CambiarPass` (`pidempleado` INT, `pcontraseña` TEXT)  BEGIN
		UPDATE empleado SET
			contraseña=pcontraseña
		WHERE idempleado = pidempleado;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Categoria` (`pidcategoria` INT, `pdescripcion` VARCHAR(100))  BEGIN
		UPDATE categoria SET
			descripcion=pdescripcion	
		WHERE idcategoria = pidcategoria;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Cliente` (`pidcliente` INT, `pnombre` VARCHAR(100), `pruc` VARCHAR(11), `pdni` VARCHAR(8), `pdireccion` VARCHAR(50), `ptelefono` VARCHAR(15), `pobsv` TEXT)  BEGIN
		UPDATE cliente SET
			nombre=pnombre,
			ruc=pruc,
			dni=pdni,
			direccion=pdireccion,
			telefono=ptelefono,
			obsv=pobsv
		WHERE idcliente = pidcliente;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Compra` (`pidcompra` INT, `pidtipodocumento` INT, `pidproveedor` INT, `pidempleado` INT, `pnumero` VARCHAR(20), `pfecha` DATE, `psubtotal` DECIMAL(8,2), `pigv` DECIMAL(8,2), `ptotal` DECIMAL(8,2), `pestado` VARCHAR(30))  BEGIN
		UPDATE compra SET
			idtipodocumento=pidtipodocumento,
			idproveedor=pidproveedor,
			idempleado=pidempleado,
			numero=pnumero,
			fecha=pfecha,
			subtotal=psubtotal,
			igv=pigv,
			total=ptotal,
			estado=pestado
		WHERE idcompra = pidcompra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_DetalleCompra` (`pidcompra` INT, `pidproducto` INT, `pcantidad` DECIMAL(8,2), `pprecio` DECIMAL(8,2), `ptotal` DECIMAL(8,2))  BEGIN
		UPDATE venta SET
			idcompra=pidcompra,
			idproducto=pidproducto,
			cantidad=pcantidad,
			precio=pprecio,
			total=ptotal
		WHERE idcompra = pidcompra;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_DetalleVenta` (`pidventa` INT, `pidproducto` INT, `pcantidad` DECIMAL(8,2), `pcosto` DECIMAL(8,2), `pprecio` DECIMAL(8,2), `ptotal` DECIMAL(8,2))  BEGIN
		UPDATE venta SET
			idventa=pidventa,
			idproducto=pidproducto,
			cantidad=pcantidad,
			costo=pcosto,
			precio=pprecio,
			total=ptotal
		WHERE idventa = pidventa;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Empleado` (`pidempleado` INT, `pnombre` VARCHAR(50), `papellido` VARCHAR(80), `psexo` VARCHAR(1), `pfechanac` DATE, `pdireccion` VARCHAR(100), `ptelefono` VARCHAR(10), `pcelular` VARCHAR(15), `pemail` VARCHAR(80), `pdni` VARCHAR(8), `pfechaing` DATE, `psueldo` DECIMAL(8,2), `pestado` VARCHAR(30), `pusuario` VARCHAR(20), `pcontraseña` TEXT, `pidtipousuario` INT)  BEGIN
		UPDATE empleado SET
			nombre=pnombre,
			apellido=papellido,
			sexo=psexo,
			fechanac=pfechanac,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			dni=pdni,
			fechaing=pfechaing,
			sueldo=psueldo,
			estado=pestado,
			usuario=pusuario,
			contraseña=pcontraseña,
			idtipousuario=pidtipousuario			
		WHERE idempleado = pidempleado;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Producto` (`pidproducto` INT, `pcodigo` VARCHAR(50), `pnombre` VARCHAR(100), `pdescripcion` TEXT, `pstock` DECIMAL(8,2), `pstockmin` DECIMAL(8,2), `ppreciocosto` DECIMAL(8,2), `pprecioventa` DECIMAL(8,2), `putilidad` DECIMAL(8,2), `pestado` VARCHAR(30), `pidcategoria` INT)  BEGIN
		UPDATE producto SET
			codigo=pcodigo,
			nombre=pnombre,
			descripcion=pdescripcion,
			stock=pstock,
			stockmin=pstockmin,
			preciocosto=ppreciocosto,
			precioventa=pprecioventa,
			utilidad=putilidad,
			estado=pestado,
			idcategoria=pidcategoria
			
		WHERE idproducto = pidproducto;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Proveedor` (`pidproveedor` INT, `pnombre` VARCHAR(100), `pruc` VARCHAR(11), `pdni` VARCHAR(8), `pdireccion` VARCHAR(100), `ptelefono` VARCHAR(10), `pcelular` VARCHAR(15), `pemail` VARCHAR(80), `pcuenta1` VARCHAR(50), `pcuenta2` VARCHAR(50), `pestado` VARCHAR(30), `pobsv` TEXT)  BEGIN
		UPDATE proveedor SET
			nombre=pnombre,
			ruc=pruc,
			dni=pdni,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			cuenta1=pcuenta1,
			cuenta2=pcuenta2,
			estado=pestado,
			obsv=pobsv
		WHERE idproveedor = pidproveedor;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_TipoDocumento` (`pidtipodocumento` INT, `pdescripcion` VARCHAR(80))  BEGIN
		UPDATE tipodocumento SET
			descripcion=pdescripcion	
		WHERE idtipodocumento = pidtipodocumento;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_TipoUsuario` (`pidtipousuario` INT, `pdescripcion` VARCHAR(80), `pp_venta` INT, `pp_compra` INT, `pp_producto` INT, `pp_proveedor` INT, `pp_empleado` INT, `pp_cliente` INT, `pp_categoria` INT, `pp_tipodoc` INT, `pp_tipouser` INT, `pp_anularv` INT, `pp_anularc` INT, `pp_estadoprod` INT, `pp_ventare` INT, `pp_ventade` INT, `pp_estadistica` INT, `pp_comprare` INT, `pp_comprade` INT, `pp_pass` INT, `pp_respaldar` INT, `pp_restaurar` INT, `pp_caja` INT)  BEGIN
		UPDATE tipousuario SET
			descripcion=pdescripcion,
			p_venta=pp_venta,
			p_compra=pp_compra,
			p_producto=pp_producto,
			p_proveedor=pp_proveedor,
			p_empleado=pp_empleado,
			p_cliente=pp_cliente,
			p_categoria=pp_categoria,
			p_tipodoc=pp_tipodoc,
			p_tipouser=pp_tipouser,
			p_anularv=pp_anularv,
			p_anularc=pp_anularc,
			p_estadoprod=pp_estadoprod,
			p_ventare=pp_ventare,
			p_ventade=pp_ventade,
			p_estadistica=pp_estadistica,
			p_comprare=pp_comprare,
			p_comprade=pp_comprade,
			p_pass=pp_pass,
			p_respaldar=pp_respaldar,
			p_restaurar=pp_restaurar,
			p_caja=pp_caja
		WHERE idtipousuario = pidtipousuario;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Venta` (`pidventa` INT, `pidtipodocumento` INT, `pidcliente` INT, `pidempleado` INT, `pserie` VARCHAR(5), `pnumero` VARCHAR(20), `pfecha` DATE, `ptotalventa` DECIMAL(8,2), `pdescuento` DECIMAL(8,2), `psubtotal` DECIMAL(8,2), `pigv` DECIMAL(8,2), `ptotalpagar` DECIMAL(8,2), `pestado` VARCHAR(30))  BEGIN
		UPDATE venta SET
			idtipodocumento=pidtipodocumento,
			idcliente=pidcliente,
			idempleado=pidempleado,
			serie=pserie,
			numero=pnumero,
			fecha=pfecha,
			totalventa=ptotalventa,
			descuento=pdescuento,
			subtotal=psubtotal,
			igv=pigv,
			totalpagar=ptotalpagar,
			estado=pestado
		WHERE idventa = pidventa;
	END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `IdCategoria` int(11) NOT NULL,
  `Descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`IdCategoria`, `Descripcion`) VALUES
(1, 'MEDICAMENTOS'),
(2, 'ABARROTES'),
(3, 'HARDWARE'),
(4, 'SOFTWARE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `IdCliente` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Ruc` varchar(11) DEFAULT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Obsv` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`IdCliente`, `Nombre`, `Ruc`, `Dni`, `Direccion`, `Telefono`, `Obsv`) VALUES
(1, 'PUBLICO GENERAL', '10477157770', '', 'Chiclayo', '979026684', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `IdCompra` int(11) NOT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `IdProveedor` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Numero` varchar(20) DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `SubTotal` decimal(8,2) DEFAULT NULL,
  `Igv` decimal(8,2) DEFAULT NULL,
  `Total` decimal(8,2) DEFAULT NULL,
  `Estado` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompra`
--

CREATE TABLE `detallecompra` (
  `IdCompra` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` decimal(8,2) NOT NULL,
  `Precio` decimal(8,2) NOT NULL,
  `Total` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--

CREATE TABLE `detalleventa` (
  `IdVenta` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` decimal(8,2) NOT NULL,
  `Costo` decimal(8,2) NOT NULL,
  `Precio` decimal(8,2) NOT NULL,
  `Total` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `IdEmpleado` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(80) NOT NULL,
  `Sexo` varchar(1) NOT NULL,
  `FechaNac` date NOT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Celular` varchar(15) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `FechaIng` date NOT NULL,
  `Sueldo` decimal(8,2) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `Usuario` varchar(20) DEFAULT NULL,
  `Contraseña` text,
  `IdTipoUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`IdEmpleado`, `Nombre`, `Apellido`, `Sexo`, `FechaNac`, `Direccion`, `Telefono`, `Celular`, `Email`, `Dni`, `FechaIng`, `Sueldo`, `Estado`, `Usuario`, `Contraseña`, `IdTipoUsuario`) VALUES
(1, 'Admin', 'Admin', 'M', '2013-06-15', 'Juliaca', '000000', '000000000', 'usu@gmail.com', '45889877', '2013-06-15', '750.00', 'ACTIVO', 'admin', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `IdProducto` int(11) NOT NULL,
  `Codigo` varchar(50) DEFAULT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text,
  `Stock` decimal(8,2) DEFAULT NULL,
  `StockMin` decimal(8,2) DEFAULT NULL,
  `PrecioCosto` decimal(8,2) DEFAULT NULL,
  `PrecioVenta` decimal(8,2) DEFAULT NULL,
  `Utilidad` decimal(8,2) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `IdCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `Codigo`, `Nombre`, `Descripcion`, `Stock`, `StockMin`, `PrecioCosto`, `PrecioVenta`, `Utilidad`, `Estado`, `IdCategoria`) VALUES
(1, '191976', 'Monitor para PC', 'Monitor LED Samsung', '10.00', '2.00', '80.00', '100.00', '20.00', 'ACTIVO', 3),
(2, '22', 'Teclado Multifuncional', 'Teclado ergonomico', '12.00', '3.00', '25.00', '45.00', '20.00', 'ACTIVO', 3),
(3, '374595', 'CASE INTEL Core I3-3410', 'Case color negro marca PCTronic, memoria Ram 4GB. HDD 250GB.', '10.00', '5.00', '322.58', '482.24', '159.66', 'ACTIVO', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `IdProveedor` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Ruc` varchar(11) DEFAULT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Celular` varchar(15) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `Cuenta1` varchar(50) DEFAULT NULL,
  `Cuenta2` varchar(50) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `Obsv` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`IdProveedor`, `Nombre`, `Ruc`, `Dni`, `Direccion`, `Telefono`, `Celular`, `Email`, `Cuenta1`, `Cuenta2`, `Estado`, `Obsv`) VALUES
(1, 'SIN PROVEEDOR', '', '', '', '', '', '', '', '', 'ACTIVO', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE `tipodocumento` (
  `IdTipoDocumento` int(11) NOT NULL,
  `Descripcion` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`IdTipoDocumento`, `Descripcion`) VALUES
(1, 'BOLETA'),
(2, 'FACTURA'),
(3, 'TICKET');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `IdTipoUsuario` int(11) NOT NULL,
  `Descripcion` varchar(20) NOT NULL,
  `p_venta` int(11) DEFAULT NULL,
  `p_compra` int(11) DEFAULT NULL,
  `p_producto` int(11) DEFAULT NULL,
  `p_proveedor` int(11) DEFAULT NULL,
  `p_empleado` int(11) DEFAULT NULL,
  `p_cliente` int(11) DEFAULT NULL,
  `p_categoria` int(11) DEFAULT NULL,
  `p_tipodoc` int(11) DEFAULT NULL,
  `p_tipouser` int(11) DEFAULT NULL,
  `p_anularv` int(11) DEFAULT NULL,
  `p_anularc` int(11) DEFAULT NULL,
  `p_estadoprod` int(11) DEFAULT NULL,
  `p_ventare` int(11) DEFAULT NULL,
  `p_ventade` int(11) DEFAULT NULL,
  `p_estadistica` int(11) DEFAULT NULL,
  `p_comprare` int(11) DEFAULT NULL,
  `p_comprade` int(11) DEFAULT NULL,
  `p_pass` int(11) DEFAULT NULL,
  `p_respaldar` int(11) DEFAULT NULL,
  `p_restaurar` int(11) DEFAULT NULL,
  `p_caja` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`IdTipoUsuario`, `Descripcion`, `p_venta`, `p_compra`, `p_producto`, `p_proveedor`, `p_empleado`, `p_cliente`, `p_categoria`, `p_tipodoc`, `p_tipouser`, `p_anularv`, `p_anularc`, `p_estadoprod`, `p_ventare`, `p_ventade`, `p_estadistica`, `p_comprare`, `p_comprade`, `p_pass`, `p_respaldar`, `p_restaurar`, `p_caja`) VALUES
(1, 'ADMINISTRADOR', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(2, 'CAJERO', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1),
(3, 'VENDEDOR', 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `IdVenta` int(11) NOT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Serie` varchar(5) DEFAULT NULL,
  `Numero` varchar(20) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `TotalVenta` decimal(8,2) NOT NULL,
  `Descuento` decimal(8,2) NOT NULL,
  `SubTotal` decimal(8,2) NOT NULL,
  `Igv` decimal(8,2) NOT NULL,
  `TotalPagar` decimal(8,2) NOT NULL,
  `Estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`IdCategoria`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`IdCompra`),
  ADD KEY `fk_Compra_Proveedor1_idx` (`IdProveedor`),
  ADD KEY `fk_Compra_Empleado1_idx` (`IdEmpleado`),
  ADD KEY `fk_Compra_TipoDocumento1_idx` (`IdTipoDocumento`);

--
-- Indices de la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD KEY `fk_DetalleCompra_Compra1_idx` (`IdCompra`),
  ADD KEY `fk_DetalleCompra_Producto1_idx` (`IdProducto`);

--
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD KEY `fk_DetalleVenta_Producto1_idx` (`IdProducto`),
  ADD KEY `fk_DetalleVenta_Venta1_idx` (`IdVenta`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IdEmpleado`),
  ADD KEY `fk_Empleado_TipoUsuario1_idx` (`IdTipoUsuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IdProducto`),
  ADD KEY `fk_Producto_Categoria_idx` (`IdCategoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`IdProveedor`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`IdTipoDocumento`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`IdTipoUsuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`IdVenta`),
  ADD KEY `fk_Venta_TipoDocumento1_idx` (`IdTipoDocumento`),
  ADD KEY `fk_Venta_Cliente1_idx` (`IdCliente`),
  ADD KEY `fk_Venta_Empleado1_idx` (`IdEmpleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `IdCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `IdCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `IdCompra` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `IdProveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `IdTipoDocumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `IdTipoUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `IdVenta` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_Compra_Empleado1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_Proveedor1` FOREIGN KEY (`IdProveedor`) REFERENCES `proveedor` (`IdProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_TipoDocumento1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`IdTipoDocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD CONSTRAINT `fk_DetalleCompra_Compra1` FOREIGN KEY (`IdCompra`) REFERENCES `compra` (`IdCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DetalleCompra_Producto1` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `fk_DetalleVenta_Producto1` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DetalleVenta_Venta1` FOREIGN KEY (`IdVenta`) REFERENCES `venta` (`IdVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_Empleado_TipoUsuario1` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`IdTipoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_Producto_Categoria` FOREIGN KEY (`IdCategoria`) REFERENCES `categoria` (`IdCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_Venta_Cliente1` FOREIGN KEY (`IdCliente`) REFERENCES `cliente` (`IdCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Venta_Empleado1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Venta_TipoDocumento1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`IdTipoDocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
