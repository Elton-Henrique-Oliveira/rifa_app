package br.com.gogoplay.app.core.filter;

//public class FilterGameAuth extends OncePerRequestFilter {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var servletPath = request.getServletPath();
//        if (servletPath.startsWith("/game/")) {
//            var authorization = request.getHeader("Authorization");
//            var authEncoded = authorization.substring("Basic".length()).trim();
//            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
//            var authString = new String(authDecode);
//
//            String[] credentials = authString.split(":");
//            String username = credentials[0];
//            String password = credentials[1];
//
//            var user = this.userRepository.findByUsername(username);
//            if (user == null) {
//                response.sendError(401);
//            } else {
//                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
//                if (passwordVerify.verified) {
//                    request.setAttribute("UuidUser", user.getUuid());
//                    filterChain.doFilter(request, response);
//                } else {
//                    response.sendError(401);
//                }
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
//    }
//}
