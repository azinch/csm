#!/bin/sh
exec scala "$0" "$@"
!#

args.foreach(println)


import scala.annotation.switch

class SwitchDemo
     val i = 1
     val two = 2
     val x = (i: @switch) match {
     case 1 => println("One...")
     //case `two` => println("Two...")
     case 2 => println("Two...")
     case _ => println("Default...")
   }

var az = new SwitchDemo

