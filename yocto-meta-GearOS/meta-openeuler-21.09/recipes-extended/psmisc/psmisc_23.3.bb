require psmisc.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "file://psmisc-23.3.tar.xz \
           file://0001-Use-UINTPTR_MAX-instead-of-__WORDSIZE.patch \
           "
SRCREV = "78bde849041e6c914a2a517ebe1255b86dc98772"
SRC_URI[sha256sum] = "f32bf427f3d3d6c7df251aebcc18585b880a08b978e4566cc1a6a3c49f31a901"
S = "${WORKDIR}/psmisc-${PV}"
