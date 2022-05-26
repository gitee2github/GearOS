SUMMARY = "Library for ASN.1 and DER manipulation"
DESCRIPTION = "A highly portable C library that encodes and decodes \
DER/BER data following an ASN.1 schema. "
HOMEPAGE = "http://www.gnu.org/software/libtasn1/"

LICENSE = "GPLv3+ & LGPLv2.1+"
LICENSE_${PN}-bin = "GPLv3+"
LICENSE_${PN} = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://doc/COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c \
                    file://LICENSE;md5=75ac100ec923f959898182307970c360"

SRC_URI = "file://libtasn1-4.16.0.tar.gz \
           file://dont-depend-on-help2man.patch \
           "

DEPENDS = "bison-native"

inherit autotools texinfo lib_package gtk-doc

BBCLASSEXTEND = "native nativesdk"
