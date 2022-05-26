SUMMARY = "Tool for applying a patch file"
DESCRIPTION = "patch takes a patch file containing a difference listing \
produced by the diff program and applies those differences to one or more \
original files, producing patched versions."
SECTION = "utils"
HOMEPAGE = "http://savannah.gnu.org/projects/patch/"

SRC_URI = "file://patch-2.7.6.tar.xz"
S = "${WORKDIR}/patch-${PV}"

inherit autotools update-alternatives

ALTERNATIVE_${PN} = "patch"
ALTERNATIVE_PRIORITY = "100"
