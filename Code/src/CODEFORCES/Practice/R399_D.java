import java.io.*;
import java.util.InputMismatchException;

/**
 * Created by Shreyans Sheth [bholagabbar] on 6/4/2015 at 8:04 PM using IntelliJ IDEA (Fast IO Template)
 */


public class R399_D
{
    static int[] SegTreeOR;
    static int[] SegTreeXOR;
    static int[] arr;

    public static void Build(int node, int a,int b)
    {
        if(a>b)
            return;
        if(a==b)
        {
            SegTreeOR [node]= arr[a];
            SegTreeXOR [node]= arr[a];
            return;
        }

        Build(2*node, a,(a+b)/2);
        Build(2*node, 1+ (a+b)/2,b);

        SegTreeOR[node]= SegTreeOR[2*node] | SegTreeOR[2*node+1];
        SegTreeXOR[node]= SegTreeXOR[2*node] ^ SegTreeXOR[2*node+1];
    }

    public static int QueryOR(int node, int a,int b, int i, int j)
    {
        if(a>j || b<i)
            return 0;

        if(i>=a && j<=b)
        {
            return SegTreeOR[node];
        }
        
        QueryOR(2 * node, a, (a + b) / 2, i, j);
        QueryOR(2 * node + 1, 1 + (a + b) / 2, b, i, j);
        
        return (SegTreeOR[2*node] | SegTreeOR[2*node+1]);
    }

    public static int QueryXOR(int node, int a,int b, int i, int j)
    {
        if(a>j || b<i)
            return 0;

        if(i>=a && j<=b)
        {
            return SegTreeXOR[node];
        }

        QueryXOR(2 * node, a, (a + b) / 2, i, j);
        QueryXOR(2 * node + 1, 1 + (a + b) / 2, b, i, j);

        return (SegTreeXOR[2*node] ^ SegTreeXOR[2*node+1]);
    }

    public static void main(String[] args) throws Exception
    {
        //System.setIn(new FileInputStream("E:/Shreyans/Documents/Code/CODE/SPOJ/Stdin_File_Read.txt"));
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int a=in.readInt(),m=in.readInt();
        int asize=(2<<a),stsize=asize<<2;

        arr=new int[asize+4];
        SegTreeOR=new int[stsize+4];
        SegTreeXOR=new int[stsize+4];

        for(int i=0;i<asize;i++)
        {
            arr[i]=in.readInt();
        }

        Build(1,0,asize-1);

        for(int i=0;i<m;i++)
        {


        }

        {
            out.close();
        }
    }

    //FAST IO
    private static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter
    {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream)
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer)
        {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects)
        {
            for (int i = 0; i < objects.length; i++)
            {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
            writer.flush();
        }

        public void printLine(Object... objects)
        {
            print(objects);
            writer.println();
            writer.flush();
        }

        public void close()
        {
            writer.close();
        }

        public void flush()
        {
            writer.flush();
        }
    }
}