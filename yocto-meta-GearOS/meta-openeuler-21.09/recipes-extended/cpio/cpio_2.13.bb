SUMMARY = "GNU cpio is a program to manage archives of files"
DESCRIPTION = "GNU cpio is a tool for creating and extracting archives, or copying files from one place to \
another. It handles a number of cpio formats as well as reading and writing tar files."
HOMEPAGE = "https://archive.launchpad.dev/kylin/pool/main/c/cpio/"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

SRC_URI = "file://cpio-2.13.tar.bz2 \
           file://0001-Unset-need_charset_alias-when-building-for-musl.patch \
           file://0002-src-global.c-Remove-superfluous-declaration-of-progr.patch \
		   file://CVE-2021-38185.patch	\
           "

SRC_URI[md5sum] = "9c9a604b77954afb3efda445514eeedc"
SRC_URI[sha256sum] = "fd1e6fb3c683bf82ae0db237af87376c6a376d1f6bf6564c9b335785e76106a9"

inherit autotools gettext texinfo

EXTRA_OECONF += "DEFAULT_RMT_DIR=${sbindir}"

do_install () {
    autotools_do_install
    if [ "${base_bindir}" != "${bindir}" ]; then
        install -d ${D}${base_bindir}/
        mv "${D}${bindir}/cpio" "${D}${base_bindir}/cpio"
        if [ "${sbindir}" != "${bindir}" ]; then
            rmdir ${D}${bindir}/
        fi
    fi

    # Avoid conflicts with the version from tar
    mv "${D}${mandir}/man8/rmt.8" "${D}${mandir}/man8/rmt-cpio.8"
}

PACKAGES =+ "${PN}-rmt"

FILES_${PN}-rmt = "${sbindir}/rmt*"

inherit update-alternatives

ALTERNATIVE_PRIORITY = "100"

ALTERNATIVE_${PN} = "cpio"
ALTERNATIVE_${PN}-rmt = "rmt"

ALTERNATIVE_LINK_NAME[cpio] = "${base_bindir}/cpio"

ALTERNATIVE_PRIORITY[rmt] = "50"
ALTERNATIVE_LINK_NAME[rmt] = "${sbindir}/rmt"

BBCLASSEXTEND = "native nativesdk"
