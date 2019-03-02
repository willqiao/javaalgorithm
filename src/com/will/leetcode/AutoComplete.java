package com.will.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TrieNode {
    //including current;
    String prefix = "";
    ArrayList<TrieNode> children;
    char c;
    boolean isSentence;
    int hotCount=0;
    
    public TrieNode(char c) {
        this.c = c;
    }
    
    public String toString() {
        return "TNode:" + prefix+ hotCount +  "| [ "+  children + "]";
    }
}

class AutocompleteSystem {
    final TrieNode root = new TrieNode('_');
    TrieNode current = null;
    String input = "";
    boolean restart = true;
    
    void addSentence(TrieNode n, String s, int i, int hotCount) {
        if (i == s.length()) {
            return;
        }
        if (n.children == null)  n.children = new ArrayList<TrieNode>();
        for (TrieNode tmp : n.children) {
            if (tmp.c == s.charAt(i)) {//found, go to children
                if (i == s.length()-1) {
                    tmp.hotCount +=hotCount;
                    tmp.isSentence = true;
                }
                addSentence(tmp, s, i+1, hotCount);
                return;
            }
        }
        //not found, add new child
        TrieNode child = new TrieNode(s.charAt(i));
        child.prefix = n.prefix + s.charAt(i);
        if (i == s.length()-1) {
            child.isSentence = true;
            child.hotCount = hotCount;
        }
        n.children.add(child);
        addSentence(child, s, i+1, hotCount);
    }
    
    //leaf
    List<TrieNode> findSentence(TrieNode n) {
        ArrayList<TrieNode> l = new ArrayList<TrieNode>();
        if (n.isSentence == true) {
            l.add(n);   
        }
        if (n.children == null) {	
        	return l;
        }        
        
        for (TrieNode child: n.children) {
            List<TrieNode> all = findSentence(child);
            l.addAll(all);
        }
        
//        System.out.println(n+"before"+l);
        Collections.sort(l, new Comparator() {
            public int compare(Object o1, Object o2) {
                TrieNode to1 = (TrieNode) o1;
                TrieNode to2 = (TrieNode) o2;
                
                if (to2.hotCount != to1.hotCount) {
                    return to2.hotCount - to1.hotCount;
                } else {
                    return to1.prefix.compareTo(to2.prefix);
                }
            }
        });
//        System.out.println(n+"after"+l);
        return l.subList(0, Math.min(3, l.size()));
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i < times.length;i++) {
            addSentence(root, sentences[i], 0, times[i]);
        }
        System.out.println(root);
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            addSentence(root, input,  0, 1);
            input = "";
            current = null;
            return new ArrayList<String>();
        }
        
        input += c;
        if (current == null && input.length() > 1) {
        	//if last time no found, just return null;
        	return new ArrayList<String>();
        }

        boolean found = false;
        if (current == null) {
            for (TrieNode child : root.children) {
                if (child.c == c) {
                    current = child;
                    found = true;
                    break;
                }
            }
        } else {
        	if (current.children != null) {
	            for (TrieNode child : current.children) {
	                if (child.c == c) {
	                    current = child;
	                    found = true;
	                    break;
	                }
	            }
        	}
        }
        if (found == false)  current = null;
        
        if (current != null) {
            List<TrieNode> result= findSentence(current);
            if (result != null && result.size() !=0) {
                ArrayList<String> strs = new ArrayList<String>();
                for (TrieNode tmp : result) {
                    strs.add(tmp.prefix);
                }
                return strs;
            } 
        }
        
        return new ArrayList<String>();
    }
}

public class AutoComplete {

	public static void main(String[] args) {
		AutocompleteSystem s = new AutocompleteSystem(new String[] {
				"uqpewwnxyqxxlhiptuzevjxbwedbaozz","ewftoujyxdgjtazppyztom","pvyqceqrdrxottnukgbdfcr","qtdkgdbcyozhllfycfjhdsdnuhycqcofaojknuqqnozltrjcabyxrdqwrxvqrztkcxpenbbtnnnkfhmebj","jwfbusbwahyugiaiazysqbxkwgcawpniptbtmhqyrlxdwxxwhtumglihrgizrczv","cfptjitfzdcrhw","aitqgitjgrcbacgnaasvbouqsqcwbyskkpsnigtfeecmlkcjbgduban","utsqkmiqqgglufourfdpgdmrkbippffacwvtkpflzrvdlkdxykfpkoqcb","ethtbdopotpamvrwuomlpahtveyw","jiaqkaxovsqtkpdjfbkajpvpyetuoqwnrnpjdhoojbsdvneecsdvgqpyurmsvcy","j","btbnuplyeuccjbernsfbnveillrwdbqledwvpmvdbcugkurrkabtpykhlcogeszclyfuquafouv","hndjzblegevtfkgbjttektox","gtvnlninpvenapyfgmsjdisfnmiktitrutctawosjflvzfkbegnprixzqwzcyhoovsivuwmofsveqkyosowuyamuvy","sawrirvrfrbfagreahrioaombukmdwztbpggnxd","mgdcwptvbvhzyvvumvbjjn","otjvvkegwleyyxtghwgfmlsqlhrlibdvqfinyyebotjpwoaejhtornfgikmifdmwswbqgwhcbzuhrpajxuqicegcptszct","zlondsttyvnnnnxjtoqnlktitwzurissczzbyfsbgpoawodwjpsmavaugnhqtsbeixwl","yehvdehbtmwqkmcjmvpivfzqvevkotwzvjoyfvp","bjximtpayjdcxbrnksbtfnpynzaygygdflowewprqngdadzdhxcpgapjejojrkzrutgcsfpfvpluagniqimfqddldxqiw","bysyrxfykivyauysytgxfhqcrxliulahuizjvozpywrokxujhzpauxwufcxiitukljiiclatfrspqcljjoxpxziumstnhqr","uxtvutlgqapyfltiulwrplesmtowzoyhhjhzihatpuvmutxqgxfawpwypedbz","jzgsdjdawrqfladolduldhpdpagmvllvzamypuqlrpbmhxxadqaqrqavtxeghcyysjynovkiyjtvdluttodtmtocajgttmv","mbijfkmepalhdiubposdksdmmttxblkodcdrxbnxaqebnwliatnxpwaohbwkidia","ljggggbyxwrwanhjonoramexdmgjigrtpz","cqfvkutpipxjepfgsufonvjtotwfxyn","kvseesjazssavispavchdpzvdhibptowhyrrshyntpwkez","nveuzbaosuayteiozmnelxlwkrrrjlwvhejxhupvchfwmvnqukphgoacnazuoimcliubvhv","uwrpwhfdrxfnarxqpkhrylkwiuhzubjfk","bniyggdcloefwy","ihranmhbsahqjxesbtmdkjfsupzdzjvdfovgbtwhqfjdddwhdvrnlyscvqlnqpzegnvvzyymrajvso","lscreasfuxpdxsiinymuzybjexkpfjiplevqcjxlm","uwgnfozopsygnmptdtmmuumahoungpkodwxrcvfymqpeymaqruayvqqgoddgbnhemtsjifhxwiehncswxzrghf","nyfbxgcpfrzyqwfjzgmhuohjhrjizsyjqgmertmooeiaadcmiuyyylpcosnweoyydeauazhzbeaqn","tpylrxbwnkrfxckfdlvrbytaezuzmyscpvruthuvbxjenkeolvqsrjqzizyclzmqtjvnamdansmzyspcfghfprorqprua","nhldlmxpuckxeekipkrzugatjiivtazjbjyxokksyueyjbgmrovbckbxqcqefaiavzsarbbypgmpxe","sylraqsd","xr","xkzpxkhrucyatpatkigvntohihibyisyqtkjdhatdvyvxbjttz","nvnz","blzddwxphkgqfsfzfclwytstpvpzgcdeggdwzukzirscfzcteeuqbmmrfxcnokbbyxkqrxtjfarcefiynwfmy","inuxmuhtdwpuvyludwtokhtalxbuccepsayrjycbcwbtnfholjvkmypodv","awwillrm","xznodxngrstjrwqzmlmigpw","khlxjdtictufdfbkgfusdtaaeuspbbfmtjodflgqofzlqnulkdztflm","nlngmckslyqzjiyiexbropbxnynjcstziluewypboqhqndqsxhtnosrgrameajovsclrgwqjgnztvxrkhwnxkfrf","yroadxhxyacaexrwju","ujxlbpcbxdqrvubifnpzjmmkolyljzjhdegaiowaal","tnfnjgtxbckbpyplucprxcqzhrfjimylmlhdglntfydepltjvklyxesndzuubienhvuaqc","ouedhtkpkg","ygchsrrubucqffewifsxaefwocfaiiupqbomktvrcddggqfgnaycstpccwtbheyaqwhosxajqeqqxzyjsfng","jqqgpjvfkgjh","csowoazaiyejgyixszqmtctpzlkccccqregyhtvxccvrpkupwcyhqatxscevzdfbdqnuyadiyfnhysddfyxpgqtjiogmxsmzbbkr","dlzxdpchkdaztkqtrjmuujgoiae","plcjkwukkyqluxjbhxsyeaqvviinfuujsafwsquidvmutsrukxwrv","yopqbtpoqhpcktjangauzcvvpephhprpaaclbbkgchlqkrwdsaupeizlwxzcpkchoagmrrkwdkthosmrjefgbumnrjsb"}, new int[] {
						12,9,4,4,1,5,3,4,7,9,2,4,2,3,11,13,1,3,4,10,7,1,9,5,10,14,5,3,2,11,5,14,4,13,11,5,15,8,1,12,2,11,4,2,11,14,9,12,1,7,13,11,7,2,6,10});
		String input = "wofqmpkqgisav#bkjordwhiagpxmkrqlvlcvwepdixjhkvaymjjrnfhrgomsddhxgsklfmsxtinsimwcfffcfhjmr#hefywctpfjwqtkblw#knufgujtinkorcvouwfynbxaomnejaulnlmefwngkhziqnfmxdvjwowbvprbkflyopyugbolvgngwixfjermhy#yvkajxbcivkyauebaqk#xxlcymzevockzoydgbefkvzhehwiokbsqtakiab#hmrtwpkwhrjpwyhfoqtwpwmkhqfbtyjzcwcstjzmkjuutojzemkhzuzydicyqdxrkdijyuidovsrngezgngryxnexrmlgnmmx#zpjlggqvmaauwlrfbqeqpbbzpduwhvtwqvjdzvhqmdwzqmfmnsgfdbxyfszmokiinlesvhmgfqrmewaxfhcaunw#khikpvuyqztmbljuprtcvwivyzlxpvpjgasguxnjqumufelo#jrgnranycwnrqutdakmednwryijkrdqvututqpufjjepsxdfmassbwutddfokhaocbfimvuevolvbknrdgcvsoxtpwngbvr#bcpfysxaecmabfeisqryyvdtkbmzuflujgfqioj#hsutrkoebpkedshlklabbmns#yvazpmvaaxpxbetvucibfzhemcicfrkldrdalzheacuuhzchwzsmtogtbizdgyhxccmevgowqw#xxzqjqatehybzhiyajgwnqxsnbdwjdbyxncto#yqxrjvehubkdahsxziqbpjvxaftowrpcuejhnwpwclfquopi#kobxpxxfxrsdsvqkhqwxukprfxnqjcncgzqxodapfsimhplcdhqvst#sfwqxaevcfeueyqkyjkpiizuobrfdvmbukkynitcgbsunxzfnhlmsmfidpzewezkrzsswkivwpmf#ulzfgompnwvshkuqppzusrpdiowbnahezgwhgzbijkyndwwjsokzpoknijrpqedeldgyvlxoonfvvyxpmswjxohrpskew#petfmgskevktfwwauzsnhamyu#ipjqpcsvlfjaomrciwkgvzhtwtwaohcmkwivtwxh#luj#ottsbwimbrluxzcdfddxeabpzvcyekxbtczjxoscxgdeyiwnycyeywvfubgnxydatpzonobnsxembenwuwsogv#emkgg#vzvwlzfvkqhcdvilbsrnrcvbfjolczfvsgywgeqvjpsfcwshdlzfgwwlvgtyklzdoxm#zhkjfuixlnpyjenmqznywfxriyxtiffffvyqkvakpmwmvknocbseenacylhamrdgvwiv#dqxldwp#grifxmrpjogbeemlwmjxbeaeayzjvbrzuml#mplhafmtyjgotnoneawcqjzqcpi#xhefzexangbbzalcdialvyqirycnmaiy#osrnrrcndsxjrlpdsoixwbgapqvcbxnflcfydxlgjybitzwdxobqgshyzhxarhqmpqpjzbgpqfhxjgckacqrdcepshtzwv#rphofltpricknimyyowpmcauwzrztpfajiotjfpmryuejeestwoqsejfiklxddvuffmexahoycwgfvxizfgqrtk#alklujcnfddqwpjpxxyzvkcfbwzemaospnwvscxlyrceuzfbqoxgpt#hcbjzryisdehibuwkoieyykbjlgeeakkmyifgntbszfptyeuaqojfutcpxlzvpgi#btxemgbvqiuhlnaqiyhiwdqjomtcymjx#naualvdufvfxfuasruujmkuxhmmifjawfptlenjyjwjtvoflzgaugayxeriwnapovpormvsacrsohifghbiaqyzqhtuzwrlgnctx#liobxjyvfkfnrjxqodduwnpomccemgpeqouhzmgtkvkbajoxbigqxdlstkjnqncqdcmbdjyezidvovqjxgwqznbywhm#krmtnejxddzgbafcubbjfdvkwvofbewktvdvjfpqmigpogmiwxtlrlwqfgsaelvghllrcpvptpmuckljvozzgsznwioazfdyhf#xamxnsrbhczotmurcclatfxbryiatpijcjxhewwjjoygcxpmcolnctjtsdwsasdmncfypvxjsvxdpficjxrvbcrqrndlfvt#nbxtwxxfpbtespklomtqiepljmwaxhcapteydwsthlzfozzahhjpz#ywfmp#swrwybipesvhnopejxsrhehtwwwyzfbonxfhgxdsmbnxjzdgqagkmuuntrbdhvxcckqcvogoaeimolhdd#grkinukdqjiiisqakhecpgjpacjpefwjvghvbmbrsfnzdiqeiyukjkkkcqar#ovzdlvxnhtbcaqmhtcmjkqltuzaglbazddbrvkxirogxyxskfn#mknzgyskplivplwlymnzahdxp#xefcvymnwlvudouzqsbsohabkivayjfsyknqvtnbrsqrppm#ovfiepzsofodvls#djqkkdoat#s#dtziufqygqwzfueznvgenzizovqalwhtqejuxfsapkbnuvhphyw#ypspegbvsdrmoiecolkmig#wbehnpjmykwcydkzplludgnljsdyytsyncfvmrurzrkvbolwtjrbv#bucowkyliby#ahuyaagzekduiecgtmgxggasqnbgoeyrxpkoqeixtskgl#porixanslccxbflshpuglmxakhidhnmwucywjxanrbmuzzoziwwybivmngtddokuxvrxubqfsppvvkzzflgomyojhyh#ksznbzsaujxedbdychlgcutltebggkyikphtuiixlbjmddpnjumqybyntydsi#sxnesibiotuoiuhafkwkdqxeudfpphktovbewaeasdffldkzgutwoojvcjwvidkrppoodcaspjklynil#";
		
				input = "wu#u";		
//		AutocompleteSystem s = new AutocompleteSystem(new String[] {
//				"abc","abbc","a"}, new int[] {
//				3,3,3});
////      String input = "bc#bc#abc#abc#";
//		String input = "a";
		
		for (char c : input.toCharArray()) {
			System.out.println(c + " | "  +s.input(c));
		}
//		       b c  #   b       c    #   a    
//		[null,[],[],[],["bc"],["bc"],[],["a"],["abbc","abc"],["abc"],[],["a"],["abc","abbc"],["abc"],[]]
//				Expected
//		[null,[],[],[],["bc"],["bc"],[],["a","abbc","abc"],["abbc","abc"],["abc"],[],["abc","a","abbc"],["abc","abbc"],["abc"],[]]
	}

}
