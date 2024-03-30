# notification-server


http://localhost:7071/h2-console

jdbc url : jdbc:h2:mem:testdb
username : sa
password : password

# SMTP Server setup on local

To set up a Postfix SMTP server on your M2 MacBook, you'll need to follow these general steps. Please note that configuring email servers can be complex, and it's important to ensure your server is properly secured and configured to avoid being used for spam or other malicious purposes.

Install Postfix: Postfix is typically pre-installed on macOS. If it's not, you can install it using Homebrew by running:

<>
brew install postfix
<>

Configure Postfix: Edit the Postfix configuration file /etc/postfix/main.cf using a text editor. Here's a basic configuration:

makefile
Copy code
# Set the hostname and domain name
myhostname = yourhostname.local
mydomain = yourdomain.com

# Set the queue directory
queue_directory = /private/var/spool/postfix

# Set the command directory
command_directory = /usr/sbin

# Set the daemon directory
daemon_directory = /usr/libexec/postfix

# Set the data directory
data_directory = /var/lib/postfix

# Set the mail owner
mail_owner = _postfix

# Specify the network interface(s) to bind to
inet_interfaces = all

# Reject unknown local recipients
unknown_local_recipient_reject_code = 550
Restart Postfix: After making changes to the configuration, restart Postfix for the changes to take effect:


sudo postfix stop
sudo postfix start


Configure SMTP Relay (Optional): If you want to relay emails through another SMTP server (e.g., Gmail), you can configure Postfix to use it as a relay host. Edit the /etc/postfix/main.cf file and add:


relayhost = [smtp.gmail.com]:587
smtp_sasl_auth_enable = yes
smtp_sasl_password_maps = hash:/etc/postfix/sasl_passwd
smtp_sasl_security_options = noanonymous
smtp_use_tls = yes
Create /etc/postfix/sasl_passwd file and add your relay host credentials:


[smtp.gmail.com]:587    username@gmail.com:password
Run the following commands to update the Postfix configuration:


sudo postmap /etc/postfix/sasl_passwd
sudo postfix stop
sudo postfix start
Testing: You can test your Postfix configuration by sending an email using the mail command:


echo "This is a test email" | mail -s "Test Email" your-email@example.com
Remember to replace yourhostname.local, yourdomain.com, smtp.gmail.com, username@gmail.com, and your-email@example.com with your actual values. Also, ensure your firewall allows traffic on the SMTP port (usually port 25).


# In macOS, Postfix logs are typically written to the system log (syslog) by default. To enable more detailed logging for Postfix, you can modify the syslog configuration to direct Postfix logs to a separate file. Here's how you can do it:

sudo launchctl list | grep syslog

sudo launchctl stop com.apple.syslogd
sudo launchctl start com.apple.syslogd

# POST Fix start and stop

sudo postfix stop
sudo postfix start


# POSTFIX main.cf

myhostname = mailserver.notification.com
myorigin = $myhostname

mydestination = $myhostname, mailserver.notification.com, localhost.localdomain, localhost
relayhost = mail.notification.com:25
mynetworks = 127.0.0.0/8, [::1]/128

smtpd_client_restrictions = permit_mynetworks permit_sasl_authenticated permit
recipient_delimiter = +
tls_random_source = dev:/dev/urandom
smtpd_tls_ciphers = medium

inet_interfaces = all
smtpd_use_tls=yes
smtpd_tls_cert_file=/etc/certs/mailserver.notification.com.crt
smtpd_tls_key_file=/etc/ssl/private/mailserver.notification.com.key
smtpd_tls_security_level=may

smtp_tls_CApath=/opt/homebrew/etc/ssl/
smtp_tls_security_level=may
smtp_tls_session_cache_database = btree:${data_directory}/smtp_scache

smtpd_relay_restrictions = permit_mynetworks permit_sasl_authenticated permit_inet_interfaces defer_unauth_destination
smtpd_recipient_restrictions = permit_mynetworks permit_sasl_authenticated

smtp_sasl_auth_enable = yes
smtp_sasl_password_maps =  hash:/etc/postfix/sasl_passwd
smtp_sasl_security_options = noanonymous

sender_canonical_maps = hash:/etc/postfix/sender_canonical

virtual_maps = hash:/etc/postfix/virtual

home_mailbox = Maildir/

sender_dependent_relayhost_maps = hash:/etc/postfix/relayhost_map
mail_owner = _postfix
setgid_group = staff


sudo postmap /etc/postfix/sasl_passwd

echo "This is the body of the email" | mail -s "Subject" recipient@example.com

