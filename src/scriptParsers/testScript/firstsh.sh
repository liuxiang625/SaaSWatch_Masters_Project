#OS=`uname`
#netIf=`ifconfig | grep "Link encap:Ethernet" | cut -d' ' -f1`
#echo "$OS"
#echo "$netIf"
#sudo ngrep -O ./dns.dump -d $netIf -T port 80 &
#sudo ngrep -O ./dns.dump -d en1 -T port 80  & 
#_pid=$!
#echo "$_pid"
#echo "$$"
#_pid="`jobs -l | awk '{print $2}'`"
#echo "$$"
#sleep 5
#disown $_pid
#sudo kill -9 $_pid
#exit 1
bash -c '(sleep 10; sudo kill $$) & exec sudo ngrep -O ./ngrep.dump -d en1 -t port 80'