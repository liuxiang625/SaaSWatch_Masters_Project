#!/bin/sh -- # -*- perl -*-
eval 'exec perl $0 ${1+"$@"} ;'
if 0;
print "Hello world!\n";
exec("ngrep -O ./dns.dump -d en1 -T port 80");