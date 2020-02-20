sudo su
# dnf update -y
# dnf upgrade -y
dnf install postgresql-server postgresql-contrib -y
# vim vi nano -y
systemctl enable postgresql
postgresql-setup --initdb --unit postgresql
sudo sh -c 'echo "host all all 0.0.0.0/0 md5" >> /var/lib/pgsql/data/pg_hba.conf'
sudo sh -c "echo \"listen_addresses = '*'\" >> /var/lib/pgsql/data/postgresql.conf"
sudo systemctl restart postgresql
su - postgres -c "psql -U postgres -d postgres -c \"alter user postgres with password 'admin';\""
su - postgres -c "psql -U postgres -d postgres -c \"create database banconormal;\""
su - postgres -c "psql -U postgres -d postgres -c \"create database bancoteste;\""
shutdown -r now
