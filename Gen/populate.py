#!/usr/bin/python

from linecache import getline
from random import randint
from datetime import timedelta, datetime
import time

MAX_N = 250
NAME_PATH = "nombres.txt"
LAST_PATH = "apellidos.txt"

SALARIO_MIN = 10000
SALARIO_MAX = 80000

def main():
	people = {}
	p = {}
	sql = ""
	for x in xrange(MAX_N):
		p = random_worker(people)
		sql = ""
		sql += "INSERT INTO p.trabajador VALUES "
		sql += "({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7});"
		sql = sql.format( \
			p['ci'], p['nombre'], p['fecha_nacimiento'], \
			p['cargo'], p['sueldo'], p['fecha_inicio'], \
			p['fecha_fin'], p['rif'] \
		)

		print sql

def random_worker(people):
	p = {}
	p['ci'] = random_ci(people)
	p['nombre'] = random_name()
	p['fecha_nacimiento'] = random_date('1950-1-1', '1998-12-31')
	p['cargo'] = random_item(jobs) 
	p['sueldo'] = random_salary()
	p['fecha_inicio'] = random_date('1999-02-02', time.strftime("%Y-%m-%d"))
	p['fecha_fin'] = random_date((datetime.now() - timedelta(days=366)).strftime('%Y-%m-%d'), '2020-12-31')
	p['rif'] = random_item(rifs)
	return p

def random_ci(people):
	while True:
		ci = str(randint(1, 30000000))
		if people.get(ci) == None:
			break
	return ci

def random_name():
	name = getline(NAME_PATH, randint(0, 461))
	last = getline(LAST_PATH, randint(0, 105))
	return name[:len(name)-1] + " " + last[:len(last)-1]

def random_date(ini, fin):
	format = "%Y-%m-%d"
	start = datetime.strptime(ini, format)
	final = datetime.strptime(fin, format)
	return (start + timedelta(seconds=randint(0, int((final - start).total_seconds())))).strftime("%Y-%m-%d")

def random_item(array):
	return array[randint(0, len(array)-1)]

def random_salary():
	return float(randint(SALARIO_MIN, SALARIO_MAX) + 2 ** (randint(0, 15)))

jobs = [
	'Gerente',
	'Programador',
	'Conserje',
	'Empaquetador'
	'Mensajero',
	'Director de planta',
	'Corredor de envios',
	'Director de personal',
	'Secretaria',
	'Director de riesgos',
	'Director de seguridad',
	'Operador de maquinaria',
	'Soporte tecnico'
]

rifs = [
	'abcd',
	'acdb',
	'bdgs',
	'bhgh',
	'xyqw',
	'swdr',
	'juyr',
	'cgui',
	'acbj',
	'plrt',
	'vkrw',
	'plqn'
]

if __name__ == '__main__':
	main()