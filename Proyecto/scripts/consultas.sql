/*Consultas*/
				
/*Indique toda la información del producto más costoso que se produce 
en la “Chocolatera Cimarrón” y muestre las distintas 
etapas de producción y qué trabajadores están vinculados en cada etapa.*/

select 
	pmax.codigo,
	pmax.nombre,
	pmax.peso,
	pmax.precio,
	pmax.tipo,
	et.nombre as etapa,
	t.nombre as trabajador
from
	p.etapa et,
	p.trabajador t,
	p.procesa pcs,
	p.labora l,
	(select 
		pr.codigo,
		pr.nombre, 
		pr.peso, 
		pr.precio, 
		pr.tipo,
		e.rif
	from 
		p.producto pr, 
		p.procesa pc, 
		p.empresa e
	where 
		pr.codigo = pc.codigo and 
		pc.rif = e.rif and 
		e.tipo = 'Chocolatera' and 
		e.nombre = 'El Cimarron' and 
		pr.precio = (select 
						max(precio) 
					from 
						p.producto pr, 
						p.procesa pc, 
						p.empresa e 
					where 
						pr.codigo = pc.codigo and 
						pc.rif = e.rif and 
						e.tipo = 'Chocolatera' and 
						e.nombre = 'El Cimarron')) pmax
	where
		pcs.codigo = pmax.codigo and
		pcs.id = et.id and
		pcs.rif = pmax.rif and
		et.id = l.id and 
		l.ci = t.ci;


/*Liste los productos vendidos en el mes de mayo de 2016 
y las ganancias de cada Tienda “Cacao Venezolano” clasificadas 
por estado. En cada caso, los listados deben estar ordenados por 
la clave del producto en forma ascendente y por la ganancia
de forma descendente.*/

/*select 
	v.codigo, 
	v.costo, 
	w.estado 
from 
	p.vende v, 
	p.empresa em, 
	(select 
		e.estado 
	from 
		p.empresa e 
	where 
		e.tipo = 'Tienda' and 
		e.nombre = 'Cacao Venezolano' 
	GROUP BY 
		e.estado) w
where 
	v.fecha >= '2016-05-01' and 
	v.fecha <= '2016-05-31' and 
	w.estado = em.estado and 
	v.rif = em.rif 
order by 
	v.codigo asc, 
	v.costo desc;*/


select 
	v.codigo,
	sum(v.costo - pro.precio * v.cantidad) over(partition by e.estado) as ganancia,
	e.estado
from
	p.vende v,
	p.empresa e,
	p.producto pro
where
	e.tipo = 'Tienda' and 
	e.nombre = 'Cacao Venezolano' and
	v.rif = e.rif and
	v.fecha >= '2016-05-01' and 
	v.fecha <= '2016-05-31' and
	pro.codigo = v.codigo
order by 
	v.codigo asc,
	ganancia desc;
	

/*Diga cuál es el producto que ha ganado mayor cantidad de
premios e indique toda la información del evento y de los premios
otorgados.*/

																														
SELECT 
	x.codigo, 
	x.organizador, 
	x.nombre, 
	x.anho AS año, 
	x.premio, 
	pr.participante, 
	pr.delegado
FROM 
	(SELECT 
		codigo
	FROM 
		p.premio_conc 
	GROUP BY 
		codigo 
	HAVING 
		COUNT(*)=(SELECT 
					MAX(COUNT(codigo)) OVER() 
					FROM 
						p.premio_conc 
					GROUP BY 
						codigo 
					LIMIT 1)) pre, 
	p.premio_conc x, 
	p.concursa c, 
	p.evento e,
	p.participante_ev pr
WHERE 
	x.codigo = pre.codigo and
	x.codigo = c.codigo and
	x.nombre = c.nombre and
	x.organizador = c.organizador and
	x.anho = c.anho and 
	e.nombre = c.nombre and 
	e.organizador = c.organizador and
	e.anho = c.anho and
	pr.participante = 'Venezuela' and
	pr.nombre = e.nombre and
	pr.organizador = e.organizador and
	pr.anho = e.anho;


	/*c.codigo = pre.codigo AND  
	e.nombre = c.nombre AND 
	e.organizador = c.organizador AND 
	e.anho = c.anho AND 
	x.codigo = pre.codigo and
	pr.participante = 'Venezuela' and
	pr.nombre = e.nombre and
	pr.organizador = e.organizador and
	pr.anho = e.anho
	x.;*/
	
																																
