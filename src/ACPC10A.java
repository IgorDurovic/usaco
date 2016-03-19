import java.io.*;

class ACPC10A {
    public static void main(String[] args) throws IOException{
        Reader scn = new Reader();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int[] sequence = new int[3];
        int counter = 0;
        int step;

        while(true){
            for(int i = 0; i < 3; i++){
                sequence[i] = scn.nextInt();
                if(sequence[i] == 0){
                    counter++;
                }
            }
            if(counter == 3){
                break;
            }
            if(sequence[0] != 0 && sequence[1] % sequence[0] == 0){
                step = sequence[1] / sequence[0];
                if(sequence[1] * step == sequence[2]){
                    pw.println("GP " + sequence[2] * step);
                }
                else{
                    step = sequence[1] - sequence[0];
                    pw.println("AP " + (sequence[2] + step));
                }
            }
            else{
                step = sequence[1] - sequence[0];
                pw.println("AP " + (sequence[2] + step));
            }
            counter = 0;
        }

        pw.flush();
        pw.close();
        scn.close();
        System.exit(0);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte [] buffer;
        private int bufferPointer, bytesRead;

        public Reader () {
            din = new DataInputStream (System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader (String file_name) throws IOException {
            din = new DataInputStream (new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine () throws IOException {
            byte [] buf = new byte[1024];
            int cnt = 0, c;
            while ((c = read ()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String (buf, 0, cnt);
        }

        public int nextInt () throws IOException {
            int ret = 0;
            byte c = read ();
            while (c <= ' ')
                c = read ();
            boolean neg = (c == '-');
            if (neg)
                c = read ();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read ()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong () throws IOException {
            long ret = 0;
            byte c = read ();
            while (c <= ' ')
                c = read ();
            boolean neg = (c == '-');
            if (neg)
                c = read ();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read ()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble () throws IOException {
            double ret = 0, div = 1;
            byte c = read ();
            while (c <= ' ')
                c = read ();
            boolean neg = (c == '-');
            if (neg)
                c = read ();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read ()) >= '0' && c <= '9');
            if (c == '.')
                while ((c = read ()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer () throws IOException {
            bytesRead = din.read (buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read () throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer ();
            return buffer[bufferPointer++];
        }

        public void close () throws IOException {
            if (din == null)
                return;
            din.close ();
        }
    }

}
