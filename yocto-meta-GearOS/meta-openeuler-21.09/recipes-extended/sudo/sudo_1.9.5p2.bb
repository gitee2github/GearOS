require sudo.inc

SRC_URI = "file://sudo-1.9.5p2.tar.gz	\ 
           ${@bb.utils.contains('DISTRO_FEATURES', 'pam', '${PAM_SRC_URI}', '', d)} \
           "

PAM_SRC_URI = "file://sudo.pam"

SRC_URI[sha256sum] = "7ea8d97a3cee4c844e0887ea7a1bd80eb54cc98fd77966776cb1a80653ad454f"

DEPENDS += " virtual/crypt ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
RDEPENDS_${PN} += " ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam-plugin-limits pam-plugin-keyinit', '', d)}"

CACHED_CONFIGUREVARS = " \
        ac_cv_type_rsize_t=no \
        ac_cv_path_MVPROG=${base_bindir}/mv \
        ac_cv_path_BSHELLPROG=${base_bindir}/sh \
        ac_cv_path_SENDMAILPROG=${sbindir}/sendmail \
        ac_cv_path_VIPROG=${base_bindir}/vi \
        "

EXTRA_OECONF += " \
             ${@bb.utils.contains('DISTRO_FEATURES', 'pam', '--with-pam', '--without-pam', d)} \
             ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--enable-tmpfiles.d=${nonarch_libdir}/tmpfiles.d', '--disable-tmpfiles.d', d)} \
             --with-rundir=/run/sudo \
             --with-vardir=/var/lib/sudo \
             --libexecdir=${libdir} \
             "

do_install_append () {
	if [ "${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)}" ]; then
		install -D -m 644 ${WORKDIR}/sudo.pam ${D}/${sysconfdir}/pam.d/sudo
		if ${@bb.utils.contains('PACKAGECONFIG', 'pam-wheel', 'true', 'false', d)} ; then
			echo 'auth       required     pam_wheel.so use_uid' >>${D}${sysconfdir}/pam.d/sudo
			sed -i 's/# \(%wheel ALL=(ALL) ALL\)/\1/' ${D}${sysconfdir}/sudoers
		fi
	fi

	chmod 4111 ${D}${bindir}/sudo
	chmod 0440 ${D}${sysconfdir}/sudoers

	# Explicitly remove the /sudo directory to avoid QA error
	rmdir -p --ignore-fail-on-non-empty ${D}/run/sudo
}

FILES_${PN}-dev += "${libdir}/${BPN}/lib*${SOLIBSDEV} ${libdir}/${BPN}/*.la \
                    ${libdir}/lib*${SOLIBSDEV} ${libdir}/*.la"

SUDO_PACKAGES = "${PN}-sudo\
                 ${PN}-lib"

PACKAGE_BEFORE_PN = "${SUDO_PACKAGES}"

RDEPENDS_${PN}-sudo = "${PN}-lib"
RDEPENDS_${PN} += "${SUDO_PACKAGES}"

FILES_${PN}-sudo = "${bindir}/sudo ${bindir}/sudoedit"
FILES_${PN}-lib = "${localstatedir} ${libexecdir} ${sysconfdir} ${libdir} ${nonarch_libdir}"
