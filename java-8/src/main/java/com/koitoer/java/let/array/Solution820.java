package com.koitoer.java.let.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution820 {

    @Test
    public void test() {

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "abbccbba", "bbc", "cbba" })).isEqualTo(13);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "abbccbba", "bbc", "bb" })).isEqualTo(16);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "abbccbba", "bbc" })).isEqualTo(13);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "aabbcbbc", "bbc" })).isEqualTo(9);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "aabbcbb", "bbc" })).isEqualTo(12);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "time", "me", "bell" })).isEqualTo(10);
        //System.out.println();
        Assertions.assertThat(new Solution820().minimumLengthEncoding(new String[] { "me", "time", "bell", "time", "me" })).isEqualTo(10);

        Assertions.assertThat(new Solution820().minimumLengthEncoding(
            new String[] { "gtgwzg", "bgmwmrk", "nqslwdi", "nwsfvi", "ixfez", "muovikm", "cfxptlx", "nffdyw", "zrmtvv", "odmhe",
                "btupmf", "sjfmx", "pytwab", "kznqxp", "jngry", "ppivkj", "bwwmqpq", "lxbnu", "altks", "motdd", "jimgy", "lppjek", "kbanc",
                "lxtgvb", "uqvvek", "ntpxnyn", "qlrdcx", "xcmgzwt", "gtcapjg", "sntqu", "tkfwow", "xqbja", "fyqbiw", "ruawk", "frjdyp",
                "txknwrh", "kzyjg", "bttxz", "lgntv", "ewfxgz", "lchzsg", "yqfoa", "zhsbm", "htxcg", "qjqkxou", "gkcxv", "lhsjs", "igrtnjv",
                "ifuecww", "slzcs", "yceue", "retyxs", "klybm", "jbxjv", "erhosw", "bjhjpjr", "nvwkcq", "mezursm", "ykbvin", "xzlij",
                "uiopt", "zyuxddz", "rmfhp", "xfltr", "csluqps", "gzuvj", "oyqyjy", "lgjuw", "hytegp", "gkoxj", "boirzbg", "dsqre",
                "gxrgabo", "jdlab", "kchijrb", "kuozwmp", "vrjqov", "hfmehfl", "xkonfn", "yfhkp", "ocota", "akfao", "qllffp", "etrpndt",
                "nrnmeh", "kaemhl", "diqeja", "wxclkjl", "bggfny", "krvmmx", "wofbj", "dliqwvn", "fcihtkt", "fonqx", "irawity", "kkmlx",
                "gjmshvq", "llcov", "vyqbaz", "ypprher", "erzcn", "zdzmj", "secthxg", "dnyxtvn", "ivqrk", "xzstj", "tvmepa", "rweifqm",
                "tjvoeme", "lquuq", "xeulv", "gxmyfrx", "ahltke", "hgbgr", "gtddmv", "dbrol", "tmqlk", "bmfaok", "iqojj", "zowni",
                "gwkvkgs", "mtoxm", "wyinnug", "kfotoix", "duymz", "keywrvl", "mukloly", "lfcycan", "illypju", "jfmlw", "atmaai", "lcsrk",
                "aarsej", "gylent", "cvoorpa", "awpczi", "vsrerd", "gngvhu", "fhwkc", "wsvftqb", "yevwb", "vgcpwb", "zymvkkh", "bziergz",
                "regjz", "ajhdn", "bgqwre", "kqfqax", "cjyly", "vinwbe", "ymkbtst", "oavwn", "onjzg", "qssxa", "eiakw", "zpcke", "ifgotsq",
                "adjdprf", "ilbtt", "vcjgw", "zzbjnin", "nztco", "dbyruh", "nvsvk", "jgits", "erzqz", "leqns", "twrrlp", "rqymf", "vmzijn",
                "kegnmyk", "oqbmcg", "otzae", "wtnls", "gstgfvy", "pqcjlui", "mxuitc", "tthpkeo", "ilhfm", "mganq", "akfti", "savwla",
                "jknboxy", "gizapl", "kkogrl", "bxflyea", "djeqc", "trypds", "ifowgv", "wojnr", "zqtpjh", "hirqg", "bfssfo", "wklsjey",
                "flvmqe", "lccbypw", "vatqhl", "exoqnda", "timli", "dfqsw", "vpqofqw", "wkqmuw", "jjrak", "ehqkwsc", "aszlpxn", "pcljgad",
                "oulhg", "miuirt", "fnpbpb", "slgcj", "sobzp", "qjjaaz", "xyzqeyp", "hxcdwyz", "zoxfyc", "dpjezj", "nhlbk", "wjgbvxc",
                "vskzyvm", "yjknun", "magigau", "qdyztsp", "tzauro", "cyafd", "tueqrk", "vbsndz", "oenku", "onyxixo", "cznrfu", "vylwwl",
                "cjquqf", "dtvjbs", "hrbax", "vtfkhsv", "kgunkh", "gzoralk", "rrnyslz", "ynqxm", "cpgky", "xhasqk", "mobfn", "qzumziz",
                "gttim", "cfvghnl", "bqlna", "saaaoa", "bdejifh", "uwddhyf", "ucqde", "yahxi", "ghjytm", "pfdtj", "ncqyqz", "bwplqpm",
                "jmdprp", "wjzuh", "oyvsn", "uxlqcco", "rgxzul", "mzhgvih", "iauvz", "zpygy", "mptoxu", "veektwe", "ulaietf", "bqcymhk",
                "hibij", "fzcoy", "ksrdhev", "auvxxqg", "bkqvrj", "egkcxj", "vdczklu", "wopnw", "wtfxx", "zdryst", "yfxyi", "hdezi",
                "cwsoiof", "gwfuqg", "wikkxzu", "vakgw", "jtoqr", "qzrzgo", "ovqtjk", "fvouhbd", "iunwj", "aafavex", "wwaisl", "idcyou",
                "pmbbjxw", "icjufh", "rjzog", "exhvn", "dypdbhr", "crvpii", "uylzlrp", "sutckm", "sxakybe", "kxhlcve", "xnnbz", "xrvwgj",
                "tfzqzz", "mzknfo", "anlfzcr", "lwsdak", "eqjgkk", "njdqzuw", "bvbrtx", "jozfbs", "zenxg", "rkpaoft", "aeztwv", "zdftrrx",
                "uurpcsk", "qdxkz", "ijjuxiv", "excyqo", "qqvbfz", "aywtfnl", "zpmfprc", "rydfsn", "nvfohp", "mvbjpx", "jpbkjef", "ogwhp",
                "ubaxvg", "ofrqfy", "zyhvyo", "wlakxaa", "tnzyk", "saapyr", "tcmpeb", "etnyaq", "lrgsq", "mzpbs", "fkczm", "xxdgd", "lkibi",
                "gthxdj", "dgzld", "tnunjn", "hzzepd", "qhmoci", "dskxgx", "jfyyrzg", "hanzy", "pzier", "thxkmx", "fktcrf", "ymdjvmf",
                "hgzhek", "qcelftn", "yoget", "hsadqp", "ydiip", "lkhztjr", "pjkfbi", "wskxv", "qezzdtk", "zereg", "lcqvjym", "epdlycj",
                "lqfnau", "njehxn", "srafi", "dhfwu", "wztjr", "ucwgnqv", "wigdg", "jkzcglz", "oxxjx", "yqvlodf", "mmaltkd", "fqahe",
                "nfdef", "urxct", "hzhardk", "ugvufrl", "qoraj", "itsymq", "dxvbn", "hvgmwb", "vyoab", "wqexj", "rzimt", "aejilm",
                "fbetztv", "gzktuf", "yxhursl", "uxluoui", "vlhpj", "cazppiu", "skibpc", "iaawl", "bgpqqjw", "haworva", "dkdzrd", "oertq",
                "prphs", "pwoikd", "jzxtju", "lukslx", "mwzgsa", "zbmymir", "aarvrk", "vcnarwb", "yvbae", "mblgdx", "wpknz", "ftdowg",
                "ogayhz", "qmemfr", "ldqbre", "oexhbh", "pndmji", "lmmhxnb", "ecmutw", "zsblbn", "aimqqnz", "ubsft", "xzagmrb", "lswjdx",
                "glevzy", "kmkzoec", "kimrwg", "akote", "uovrxi", "kfocof", "sikiqtk", "iyjyf", "pkpsqu", "otsxxoe", "eoxyld", "snljhud",
                "ryuqnn", "osadi", "teqsj", "ulvrij", "kfjcz", "wvfqwon", "zkisf", "zanaxlm", "zzgegc", "dvcpuj", "qdgcjg", "ziwqwr",
                "enxcv", "cecwvep", "tqoctkw", "zwoau", "opkglae", "olpvlts", "zdtrcl", "klrvh", "obrqs", "iwykadf", "vvugv", "sxskcjo",
                "vhyeg", "ydbaeb", "tzgplyf", "bwhyp", "uwombi", "svkodw", "otszu", "bkyqfup", "rojnt", "bauroa", "bcribk", "ctihaog",
                "xktdiel", "hkvctki", "wheih", "ylhxy", "wgosp", "bvgtk", "xpclcti", "uktyodd", "gfblo", "toaur", "cxdvo", "qezdwdb",
                "mdvdk", "zyhbs", "akbydkw", "wejgqnr", "qifti", "kxalog", "nklzot", "fgoas", "apeymfw", "mdjgo", "xifpo", "hfouhi",
                "mdyyzf", "xacds", "zuijqyd", "hdhsop", "ivgfg", "uarodof", "lyqlzmc", "vcyqwnj", "uiekded", "uosqq", "quajnw", "adkri",
                "lxsbi", "porjvx", "awnkf", "sotppb", "sfhorj", "uuocxz", "vstcick", "gqvzobl", "rkccef", "rlihmff", "lazuav", "iyahmv",
                "jmuplkb", "oforwx", "ogeheqd", "qpayb", "txetvjv", "uxsgsrj", "procbt", "ehlkp", "vdtyz", "eqjyvll", "tkwrwud", "qivrv",
                "abkkllr", "khpzqpp", "cvlhohv", "nvzbx", "svvtji", "wmyveiu", "jrogfr", "zpphtie", "faoamt", "ksektw", "ujlilq", "ufuax",
                "mqjla", "fqrnf", "xcdaet", "kfqknho", "ofvdjn", "kxopf", "yysdl", "dgronv", "goknbep", "lyhuswu", "cqmvhx", "hoitpoi",
                "dgzqll", "hpyea", "xfzohrg", "ziyod", "jxkki", "vbyoxz", "ouylxxr", "mggezan", "shxvven", "yrqnj", "tzdyik", "syeaa",
                "fthdjm", "zjzvdse", "jxzdjdx", "ipxpb", "ollgnbu", "jcdjyxj", "bqltawp", "lhxyv", "dvggabj", "ahcuqje", "lnrtyaz",
                "kubtfl", "rjetqbx", "fogbvq", "tcwhba", "ksxusyg", "qlemv", "dsjnth", "zdmmncv", "sdrzpfk", "yyocl", "vtqst", "bobtwdu",
                "ivifxf", "uaxwlo", "piqiigo", "pifql", "tdhuue", "lrmdb", "peetwl", "lvwpwji", "dcibnrm", "ppamuxo", "pknto", "cbiova",
                "wdhekr", "kgipdgd", "jlpvi", "zoptbb", "yxeamj", "pjnac", "tpdprx", "bltdktt", "wtisu", "cezsmz", "bbbqxk", "hnyweo",
                "jsuuu", "bykgghi", "spulymw", "bynqe", "accaq", "lghcqe", "nqygqvx", "lfvkqbw", "duzud", "agmnlw", "gqhqc", "xkgie",
                "fumakny", "momon", "scubukr", "xrtmoe", "ywnbn", "wdkbdzv", "wyflbp", "vyaeb", "wwzzper", "veghzzz", "fdkrof", "bkjtao",
                "tbvpcis", "ftzghcq", "yjsfg", "ngdkr", "pllzc", "rgviwsm", "mydqr", "ilcjq", "kmoryr", "ocnwqw", "zxggamr", "kjlasr",
                "idjbkt", "bjvple", "oauzpzw", "gsxjp", "sehbaf", "uubptgp", "ebhaa", "ximeurd", "kqewx", "mmcaiba", "lzpqva", "viexed",
                "zbcocmi", "nxnuzv", "vyawhnk", "rozmsws", "oqbubyy", "lbvegpt", "xfxct", "jifzdqm", "bmnjwmc", "ptxcqc", "ovzsxog",
                "ylomlt", "quoic", "tlyjmyb", "fgxpcf", "wyhyzyb", "zngau", "zgsef", "phsinbl", "kgybiqo", "tvpsi", "cuxnlt", "hqrrs",
                "spkjg", "kstnc", "grcrons", "lmbjo", "mypsfq", "scwir", "ypngb", "rwqksn", "ehjufq", "yulvm", "vyqrmg", "luyto", "ueiqm",
                "tcgcqrg", "yknwn", "szrbbtv", "wupfh", "vwrmiq", "msleyih", "iqtae", "ezykx", "ilewp", "hcttjul", "esiianh", "wkuuv",
                "jszkrx", "gumys", "lketi", "zvpsb", "xsvlhst", "myywl", "svexdk", "biwsh", "kpbjcdf", "cyiwl", "ilhfm", "rvqbly", "ukowa",
                "gkmul", "krtcmi", "vwszj", "nxwipbr", "fsycct", "jeglcq", "donvsld", "bdckkdr", "iemljm", "gfpgc", "qilgqhx", "ounvam",
                "qyomyt", "zklqshf", "bpauei", "kenzs", "ytgaq", "nnepek", "tniqq", "swlbj", "ibdkeo", "oxoed", "scvcrs", "jbitcfz",
                "fjnrwjl", "jogkl", "pmeyrjc", "kahnos", "wozbzk", "ytdav", "pcley", "kjxsvub", "jfyxt", "xkttisb", "rvdhbpc", "vvbwnmi",
                "cecnlb", "jjqemu", "iasnf", "usrtyx", "vtastv", "gcbwnft", "qsiqvo", "rfbua", "utaxsxl", "msrkymm", "hjuppov", "jmhmcsc",
                "pdiujj", "eafuzlc", "srjvh", "kzrubm", "fkgzdj", "kjptq", "zrcid", "xiuqod", "nvfjea", "ioeod", "wncxt", "avbhjud",
                "qkxrl", "rbmhfcq", "shyvqbu", "ffcmv", "omfeko", "ucibm", "lexpw", "rjuqey", "qohfd", "fjgzi", "mlozc", "rstgl", "ntkraqo",
                "paykcw", "iaajb", "adpem", "gkgjbnj", "yxbuvg", "fqkxt", "jmyqte", "uzeqyj", "rumyxor", "gkfzleq", "dwngr", "thtqdtr",
                "yptnz", "xoadll", "psvhyce", "geoso", "lijtbu", "amkbuby", "gfpyw", "plkso", "owdmtvh", "fkxad", "keqdrz", "irxjure",
                "gfwepm", "wyxiom", "pyedlj", "mfszmv", "tkjiofn", "epjdigv", "jjnuh", "mtxks", "nvfts", "xtqhc", "xttlu", "sasra",
                "qfumac", "rwfavex", "rcwstl", "cfzmi", "htvxrs", "nokfsvv", "tbvtckp", "wsikt", "tyvwtw", "hyvzd", "edflcy", "wduqbbl",
                "xacrrb", "sfbzq", "adhhyy", "tltmppb", "imjooie", "joizol", "rbzhgs", "rcldg", "ikspo", "sxouwi", "llemmdb", "nkkfie",
                "uadfas", "rigylga", "mbnhs", "vwtsnh", "uiskft", "ppvhyr", "znaenz", "lbjldsk", "sizcja", "dzvlem", "iedleqf", "zhxzaqt",
                "zyeen", "ijdohjj", "dvycit", "nfqyxoc", "xgspmx", "gticsq", "tvodn", "zpvtu", "yvevn", "bugvglx", "pprlwl", "wikandw",
                "fmkqpzp", "sjfnjlf", "yswptd", "aeuqf", "ihynsy", "fnlrtb", "haewxo", "fvnzrx", "mudxoc", "vtdpd", "zuldtvj", "kerpq" }))
            .isEqualTo(6209);

    }

    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        if (words.length == 1) {
            return words[0].length() + 1;
        }

        Comparator<String> comp = (s1, s2) -> s2.length() - s1.length();

        Arrays.sort(words, comp);

        Set<String> a = new HashSet<>();
        a.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            isSubStringOfAny2(a, words[i]);
        }

        int counter = 0;
        for (String aux : a) {
            counter = counter + aux.length();
        }

        return counter + a.size();
    }

    //aabbcbb
    private void isSubStringOfAny2(Set<String> a, String word) {
        boolean shouldIAdd = false;

        for (String aux : a) {

            for(int i =0 ; i< word.length() ; i++) {
                int length = i;
                String newaux = aux.substring(aux.length()- length);
                int idx = newaux.indexOf(word);

                if (idx == -1) {
                    shouldIAdd = true;
                    break;
                }
            }
        }

        if (shouldIAdd) {
            a.add(word);
        }

    }

}
